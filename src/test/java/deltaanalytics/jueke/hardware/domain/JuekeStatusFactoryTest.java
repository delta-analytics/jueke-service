package deltaanalytics.jueke.hardware.domain;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JuekeStatusFactoryTest {
    @Test
    public void buildFrom26Bytes() {
        JuekeStatusFactory juekeStatusFactory = new JuekeStatusFactory();

        byte[] juekeByteResponse = buildJuekeResponseBytes();

        JuekeStatus juekeStatus = juekeStatusFactory.build(juekeByteResponse);

        assertThat(juekeStatus, is(equalTo(expected())));
    }

    private byte[] buildJuekeResponseBytes() {
        byte[] bytes = new byte[26];
        bytes[0] = 1;
        bytes[1] = 26;
        bytes[2] = 10;
        bytes[3] = 10;
        bytes[4] = 10;
        bytes[5] = 10;
        bytes[6] = 10;
        bytes[7] = 10;
        bytes[8] = 10;
        bytes[9] = 10;
        bytes[10] = 10;
        bytes[11] = 10;
        bytes[12] = 10;
        bytes[13] = 10;
        bytes[14] = 10;
        bytes[15] = 10;
        bytes[16] = 10;
        bytes[17] = 10;
        bytes[18] = 10;
        bytes[19] = 10;
        bytes[20] = 10;
        bytes[21] = 10;
        bytes[22] = 10;
        bytes[23] = 10;
        bytes[24] = 10;
        bytes[25] = 10;
        return bytes;
    }

    private JuekeStatus expected() {
        JuekeStatus juekeStatus = new JuekeStatus();
        juekeStatus.setStart((byte) 1);
        juekeStatus.setNumberOfBytes((byte) 26);
        juekeStatus.setStatusOfPump(false);
        juekeStatus.setPressureRegulationActive(false);
        juekeStatus.setTemperatureOfHeaterOk(false);
        juekeStatus.setHeaterRegulationActive(true);
        juekeStatus.setErrorFlags((byte) 10);
        juekeStatus.setErrorCode(10);
        juekeStatus.setValveStatus1(false);
        juekeStatus.setValveStatus2(false);
        juekeStatus.setValveStatus3(false);
        juekeStatus.setValveStatus4(false);
        juekeStatus.setValveStatus5(true);
        juekeStatus.setValveStatus6(false);
        juekeStatus.setValveStatus7(true);
        juekeStatus.setValveStatus8(false);
        juekeStatus.setPowerHeater((byte) 10);
        juekeStatus.setActualTempHeater(25.7);
        juekeStatus.setSetpointHeater(25.7);
        juekeStatus.setActualPressureCell(2570);
        juekeStatus.setSetpointPressure(2570);
        juekeStatus.setPumpPower(10);
        juekeStatus.setTempPT100_1(25.7);
        juekeStatus.setTempPT100_2(25.7);
        juekeStatus.setCounter((byte) 10);
        juekeStatus.setEnd((byte) 10);
        juekeStatus.setCrc(2570);
        juekeStatus.setRawJuekeMessage(new byte[]{1, 26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10});
        return juekeStatus;
    }
}
