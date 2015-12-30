package deltaanalytics.jueke.hardware.domain;

public class JuekeWhiteCellMessage {
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
        return new byte[]{start, command, data1, data2, data3, data4, stop,
                (byte) ((crc >> 8) & 0xff), (byte) (crc & 0xff)};
    }

}
