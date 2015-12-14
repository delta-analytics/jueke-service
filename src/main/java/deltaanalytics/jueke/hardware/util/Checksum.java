package deltaanalytics.jueke.hardware.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * calculate checksum of data stream according to cyclic reduncy check (CRC-CCIIT 0x0000) 16 bit
 * @author frank
 */
public class Checksum {
    private static final Logger LOGGER = LoggerFactory.getLogger(Checksum.class);
    private final int dataLength = 26;

    public int calculateCRC16CCITT(byte[] buffer) {
        LOGGER.info("class CheckSum: calculateCRC16CCITT = " + javax.xml.bind.DatatypeConverter.printHexBinary(buffer));

        int crc = 0x0000;          // initial value  0xFFFF makes CRC16-CCITT = 29b1 for 123456789 but we need 31c3
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12) FJ: 'divisor'
        byte[] bytes = new byte[dataLength-2];
        try{
            String str = new String(buffer, "CP858");  // ASCII, UTF-8, CP858, CP1252
            //System.out.println("Base64 encode string of byte array => " + org.apache.commons.codec.binary.Base64.encodeBase64String(bytesArray1));
            bytes = str.getBytes("CP858");
        }catch( UnsupportedEncodingException e){
            LOGGER.error("Unsupported character set");
        }
        // CRC routine
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }
        // finally
        crc &= 0xffff;

        return crc;
    }

    /**
     * calculate cheksum with CRC16CCITT method
     * @param buffer 26 bytes with 2 crc bytes
     * @return true/false
     */
    public boolean checkForConsistency(byte[] buffer) {
        byte firstByte = 0x02;
        byte lastByte = 0x03;
        if (buffer.length != dataLength ){
            LOGGER.error("checkForConsistency: not exactly 26 bytes of data available");
            return false;
        }else{
            int checksum = this.calculateCRC16CCITT(Arrays.copyOfRange(buffer, 0, dataLength - 2));  // 0=inclusive, 24=exclusive
            if ( firstByte != buffer[0]) {
                LOGGER.error("start byte not correct - 02!=" + buffer[0]);
                return false;
            } else if ( lastByte != buffer[23]) {
                LOGGER.error("end byte not correct - 03!=" + buffer[dataLength-3]);
                return false;
            } else if (checksum == bytes2Int(new byte[] {buffer[dataLength-1], buffer[dataLength-2]})) {  // low, high
                LOGGER.info("CRC correct\n");
                return true;
            } else {
                LOGGER.error("CRC not correct: calculated=" + checksum + " proof bytes="
                        + bytes2Int(new byte[] {buffer[dataLength-1], buffer[dataLength-2]}) + "\n");
                return false;
            }
        }
    }

    // helper method
    private int bytes2Int(byte[] bytes) {
        return ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
    }

}
