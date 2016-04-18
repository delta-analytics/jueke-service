package deltaanalytics.jueke.hardware.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JuekeWhiteCellMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeWhiteCellMessage.class);

    private byte command;
    private byte data1;
    private byte data2;
    private byte data3;
    private byte data4;

    public JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber command) {
        this.command = command.toByte();
    }

    public JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber command, byte data1, byte data2, byte data3, byte data4) {
        this.command = command.toByte();
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    public byte[] toByteArray() {
        if (command == 0) {
            throw new RuntimeException("Command is 0!");
        }
        byte start = (byte) 2;
        byte stop = (byte) 3;
        byte[] bytesArrayForCrc = new byte[]{start, command, data1, data2, data3, data4, stop};
        int crc = (new Checksum()).calculateCRC16CCITT(bytesArrayForCrc);
        byte[] bytes = {start, command, (byte)(data1 & 0xff), data2, data3, data4, stop,
              (byte) ((crc >> 8) & 0xff), (byte) (crc & 0xff)};
        for (byte aByte : bytes) {
            LOGGER.info(String.valueOf(aByte));
        }

        return bytes;
    }

}
