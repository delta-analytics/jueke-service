package deltaanalytics.jueke.hardware.serial;
// imports from RXTX package

import deltaanalytics.jueke.hardware.domain.Checksum;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellCommandNumber;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellMessage;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

public class JuekeSerialConnectionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnectionFactory.class);
    private static InputStream in;
    private static OutputStream out;
    private static SerialPort serialPort;

    //Example serialPortName "/dev/tty.usbserial-J0000031" for MacOsx
    public synchronized static void establishConnection(String serialPortName) {
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
                    LOGGER.info("input and output stream opened");
                } else {
                    LOGGER.error("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public synchronized static void closeConnection() {
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

    public synchronized static byte[] execute(JuekeWhiteCellMessage juekeWhiteCellMessage, int expectedResultLength, boolean onlyStatusRequest) throws Exception {
        if (in == null || out == null) {
            throw new RuntimeException("You have to establishConnection before the first execution!");
        }
        LOGGER.info("Start to jueke");
        byte[] buffer = new byte[0];
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_COM).toByteArray());

        if (onlyStatusRequest) {
            int data;
            buffer = new byte[expectedResultLength];
            int len = 0;
            LOGGER.info("read Response");
            while ((data = in.read()) > -1) {
                buffer[len++] = (byte) data;
                if (len == expectedResultLength) {
                    LOGGER.info("", buffer);
                    Checksum checkSum = new Checksum();
                    if (checkSum.checkForConsistency(buffer)) {
                        LOGGER.info("Checksum Ok");
                    } else {
                        throw new RuntimeException("Checksum wrong");
                    }
                    break;
                }
            }
        } else {
            LOGGER.info("Execute Command (not only Status Request)");
            out.write(juekeWhiteCellMessage.toByteArray());
        }

        LOGGER.info("Stop to jueke");
        out.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_COM).toByteArray());
        return buffer;
    }

}
