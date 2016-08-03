package deltaanalytics.jueke.hardware.serial;
// imports from RXTX package

import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellCommandNumber;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellMessage;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

@Component
public class JuekeSerialConnectionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnectionFactory.class);
    private static InputStream in;
    private static OutputStream out;
    private static SerialPort serialPort;
    @Autowired
    private JuekeSerialPortEventListener juekeSerialPortEventListener;

    //Example serialPortName "/dev/tty.usbserial-J0000031" for MacOsx
    public synchronized void establishConnection(String serialPortName) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            if (portIdentifier.isCurrentlyOwned()) {
                LOGGER.info("Error: Port is currently in use");
            } else {
                CommPort commPort = portIdentifier.open(JuekeSerialConnectionFactory.class.getName(), 2000);

                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);  // Default Jüke
                    in = serialPort.getInputStream();
                    out = serialPort.getOutputStream();
                    juekeSerialPortEventListener.setIn(in);
                    serialPort.addEventListener(juekeSerialPortEventListener);
                    serialPort.notifyOnDataAvailable(true);
                    LOGGER.info("input and output stream opened");
                } else {
                    LOGGER.error("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public synchronized void closeConnection() {
        if (serialPort != null) {
            try {
                serialPort.removeEventListener();
                in.close();
                out.close();
                serialPort.close();
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        }
    }

    public synchronized byte[] executeCommand(JuekeWhiteCellMessage juekeWhiteCellMessage) throws Exception {
        LOGGER.info("Start to jueke");
        byte[] buffer = new byte[0];
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_COM).toByteArray());
        LOGGER.info("send command");
        out.write(juekeWhiteCellMessage.toByteArray());
        LOGGER.info("Stop to jueke");
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_COM).toByteArray());
        LOGGER.info("", buffer);
        return buffer;
    }

    public synchronized void getStatus() throws Exception {
        LOGGER.info("Start to jueke");
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_COM).toByteArray());
        Thread.sleep(200);  // Jueke fires with 10 Hz
        LOGGER.info("Stop to jueke");
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_COM).toByteArray());
    }

}
