package deltaanalytics.jueke.hardware.factory;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Ignore
public class JuekeStatusFactoryTest {
    @Test
    public void buildFrom26Bytes(){
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
        juekeStatus.setStatusOfPump(true);
        juekeStatus.setPressureRegulationActive(true);
        juekeStatus.setTemperatureOfHeaterOk(true);
        juekeStatus.setHeaterRegulationActive(true);
        juekeStatus.setErrorFlags((byte)1);
        juekeStatus.setErrorCode(1);
        juekeStatus.setValveStatus1(true);
        juekeStatus.setValveStatus2(true);
        juekeStatus.setValveStatus3(true);
        juekeStatus.setValveStatus4(true);
        juekeStatus.setValveStatus5(false);
        juekeStatus.setValveStatus6(false);
        juekeStatus.setValveStatus7(false);
        juekeStatus.setValveStatus8(false);
        juekeStatus.setPowerHeater((byte)1);
        juekeStatus.setActualTempHeater(20.1);
        juekeStatus.setSetpointHeater(23);
        juekeStatus.setActualPressureCell(2);
        juekeStatus.setSetpointPressure(4.2);
        juekeStatus.setPumpPower(2.4);
        juekeStatus.setTempPT100_1(1);
        juekeStatus.setTempPT100_2(2);
        juekeStatus.setCounter((byte)3);
        juekeStatus.setEnd((byte) 99);
        juekeStatus.setCrc(0);
        return juekeStatus;
    }
}
