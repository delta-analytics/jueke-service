package deltaanalytics.jueke.hardware;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.hardware.util.Checksum;

public class CommandRunner {
    private int START = 2;
    private int STOP =3;

    public void setValves(ValveState valveState1, ValveState valveState2, ValveState valveState3, ValveState valveState4,
                          ValveState valveState5, ValveState valveState6, ValveState valveState7, ValveState valveState8) {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        String stateString = valveStatesToString(valveState1, valveState2, valveState3, valveState4,
                valveState5, valveState6, valveState7, valveState8);
        data[0] = Byte.parseByte(stateString, 2);
    }

    public void disablePump() {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        data[0] = (byte) 0;
    }

    public void setPumpSpeed(int speed) {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        data[0] = (byte) speed;
    }

    public void setTemperature(double temperature) {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        double temp = temperature;
        short xt = (short) (temp * 100);  // convert to 16 bit and multiply by 100
        data[0] = (byte) (xt & 0xff);
        data[1] = (byte) ((xt >> 8) & 0xff);

    }

    public void setPressure(short pressure) {
        byte[] data = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        data[0] = (byte) (pressure & 0xff);
        data[1] = (byte) ((pressure >> 8) & 0xff);
    }

    public JuekeStatus getStatus() {
        return new JuekeStatus();
    }

    private String valveStatesToString(ValveState valveState1, ValveState valveState2, ValveState valveState3, ValveState valveState4,
                                       ValveState valveState5, ValveState valveState6, ValveState valveState7, ValveState valveState8) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(valveState1.getState());
        stringBuilder.append(valveState2.getState());
        stringBuilder.append(valveState3.getState());
        stringBuilder.append(valveState4.getState());
        stringBuilder.append(valveState5.getState());
        stringBuilder.append(valveState6.getState());
        stringBuilder.append(valveState7.getState());
        stringBuilder.append(valveState8.getState());

        return stringBuilder.toString();

    }

    private byte[] buildCompleteCmd(int cmd2WC, byte[] dataBytes) {
        byte[] bytesArray = new byte[]{(byte) START, (byte) cmd2WC, dataBytes[0], dataBytes[1], dataBytes[2], dataBytes[3], (byte) STOP};
        int crc = (new Checksum()).calculateCRC16CCITT(bytesArray);
        return new byte[]{(byte) START, (byte) cmd2WC, dataBytes[0], dataBytes[1], dataBytes[2], dataBytes[3],
                (byte) STOP, (byte) ((crc >> 8) & 0xff), (byte) (crc & 0xff)};
    }
}
