package deltaanalytics.jueke.hardware.serial;
// imports from RXTX package

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

/**
 * @author frank
 * add dependency RXTX in NetBeans: goto "Dependencies" in Projects ftir_env1, right mouse click "AddDependency.." and Query: RXTX
 */
public class JuekeSerialConnection {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnection.class);
    private final int baud = 57600;
    public final List<String> portList = new ArrayList<>();
    public static boolean TEST;  // TEST variable also used in JuekeCommandService

    public JuekeSerialConnection() {
    }
    
    public void performTests(boolean performTest){
        JuekeSerialConnection.TEST = performTest;        
    }
    
    public void findPortToConnect(){
        // @ToDo make ports visible in GUI for selection
        if (!TEST){
            listPorts();
            System.out.println("enter the serial port name like COM1 or /dev/ttyS11");
            Scanner scanner = new Scanner(System.in);
            String portName = scanner.next();
            try {
                this.connect(portName);
            } catch (Exception ex) {
                LOGGER.error("JuekeSerialConnection Exception " + ex);
            }
        }        
    }

    
    void listPorts() {
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while ( portEnum.hasMoreElements() ) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
            
            // @ToDo Add ports to a list displayed to the user for selection
            if(CommPortIdentifier.PORT_SERIAL == portIdentifier.getPortType()) {
                portList.add(portIdentifier.getName());
            }
        }        
    }
    
    String getPortTypeName ( int portType ) {
        switch ( portType ) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }
    
    SerialPort connect ( String portName ) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        SerialPort serialPort = null;
        if ( portIdentifier.isCurrentlyOwned() ) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
            
            if ( commPort instanceof SerialPort ) {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baud,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);  // Default Jüke

		// same port for input and output stream
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                System.out.println("input and output stream opened");  
                
               // (new Thread(new SerialWriter(out))).start();
                System.out.println("serial writer started");
                
                serialPort.addEventListener(new SerialReader(in));  // SerialReader has interface SerialPortEventListener implemented
                System.out.println("serial reader started");
                
                serialPort.notifyOnDataAvailable(true);
            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        } 
        return serialPort;
    }
    
}
