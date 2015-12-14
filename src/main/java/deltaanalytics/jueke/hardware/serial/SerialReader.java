package deltaanalytics.jueke.hardware.serial;

import deltaanalytics.jueke.hardware.factory.JuekeStatusFactory;
import deltaanalytics.jueke.hardware.util.Checksum;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;

/**
 * Handles the input coming from the serial port. Receive data from Jüke board (26 bytes)
 * @author frank
 */
public class SerialReader implements SerialPortEventListener {
    private final int byteLength = 26;
    private final InputStream in;
    private final byte[] buffer = new byte[byteLength];

    public SerialReader ( InputStream in ) {
        this.in = in;
    }

    /*
       Reads data from input stream of serial connection specified in connect
       @ToDo who triggers the GUI to refresh incomming data?
    */
    @Override
    public void serialEvent(SerialPortEvent arg0) {
        System.out.println("SerialReader serialEvent = " + arg0);
        int data;

        try {
            int len = 0;
            while ( ( data = in.read()) > -1 )  {
                // @ToDo specify the criteria to terminate reading data
                // read 26 bytes, check if 1.byte = 02h, 24.byte = 03h, and byte-array 1..24 has CRC checksum from byte 25/26
                buffer[len++] = (byte) data;
                if ( len == byteLength ) {
                    System.out.println("Serial Reader = " + javax.xml.bind.DatatypeConverter.printHexBinary(buffer));
                    Checksum checkSum = new Checksum();
                    if ( checkSum.checkForConsistency(buffer) ) {
                        new JuekeStatusFactory().build(buffer);
                    }
                    break;
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            System.exit(-1);
        }             
    }       

}
