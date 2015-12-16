package deltaanalytics.jueke.hardware.serial;
// imports from RXTX package

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author frank
 *         add dependency RXTX in NetBeans: goto "Dependencies" in Projects ftir_env1, right mouse click "AddDependency.." and Query: RXTX
 */
public class JuekeSerialConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnectionFactory.class);
    private final int baud = 57600;


    public JuekeSerialConnection connect() throws Exception {
        InputStream in = null;
        OutputStream out = null;
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("/dev/tty.usbserial-J0000031");
        SerialPort serialPort = null;
        if (portIdentifier.isCurrentlyOwned()) {
            LOGGER.info("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);  // Default Jüke
                in = serialPort.getInputStream();
                out = serialPort.getOutputStream();
                LOGGER.info("input and output stream opened");
            } else {
                LOGGER.error("Error: Only serial ports are handled by this example.");
            }
        }
        return new JuekeSerialConnection(in, out);
    }

}
