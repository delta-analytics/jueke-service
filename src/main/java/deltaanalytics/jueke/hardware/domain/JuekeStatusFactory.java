package deltaanalytics.jueke.hardware.domain;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JuekeStatusFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeStatusFactory.class);

    public JuekeStatus build(byte[] b1) {
        JuekeStatus juekeStatus = new JuekeStatus();
        juekeStatus.setStart(b1[0]);
        juekeStatus.setNumberOfBytes(b1[1]);

        addControllerStatus(juekeStatus, new byte[]{b1[2], b1[3]});

        juekeStatus.setErrorFlags(b1[4]);
        juekeStatus.setErrorCode(new Byte(b1[4]).intValue());

        LOGGER.info("__________!____________!___________");
        LOGGER.info(String.valueOf(b1[6]));
        LOGGER.info(javax.xml.bind.DatatypeConverter.printHexBinary(b1));
        addValveStatus(juekeStatus, new byte[]{b1[6]});

        juekeStatus.setPowerHeater(b1[7]);
        juekeStatus.setActualTempHeater(bytes2Int(new byte[]{b1[8], b1[9]}) / 100.0);
        juekeStatus.setSetpointHeater(bytes2Int(new byte[]{b1[10], b1[11]}) / 100.0);
        juekeStatus.setActualPressureCell(bytes2Int(new byte[]{b1[12], b1[13]}));
        juekeStatus.setSetpointPressure(bytes2Int(new byte[]{b1[14], b1[15]}));
        juekeStatus.setPumpPower(b1[17]);
        juekeStatus.setTempPT100_1(bytes2Int(new byte[]{b1[18], b1[19]}) / 100.0);
        juekeStatus.setTempPT100_2(bytes2Int(new byte[]{b1[20], b1[21]}) / 100.0);
        juekeStatus.setCounter(b1[22]);
        juekeStatus.setEnd(b1[23]);
        juekeStatus.setCrc(bytes2Int(new byte[]{b1[25], b1[24]}));
        juekeStatus.setRawJuekeMessage(b1);
        juekeStatus.setStatusDateTime(LocalDateTime.now());
        return juekeStatus;
    }

    private void addControllerStatus(JuekeStatus juekeStatus, byte[] controllerBytes) {
        boolean[] bits = byteArray2BitArray(controllerBytes);
        juekeStatus.setStatusOfPump(bits[0]);
        juekeStatus.setPressureRegulationActive(bits[2]);
        juekeStatus.setTemperatureOfHeaterOk(bits[3]);
        juekeStatus.setHeaterRegulationActive(bits[4]);
    }

    private void addValveStatus(JuekeStatus juekeStatus, byte[] valveBytes) {
        boolean[] bits = byteArray2BitArray(valveBytes);
        juekeStatus.setValveStatus1(bits[7]);
        juekeStatus.setValveStatus2(bits[6]);
        juekeStatus.setValveStatus3(bits[5]);
        juekeStatus.setValveStatus4(bits[4]);
        juekeStatus.setValveStatus5(bits[3]);
        juekeStatus.setValveStatus6(bits[2]);
        juekeStatus.setValveStatus7(bits[1]);
        juekeStatus.setValveStatus8(bits[0]);
    }

    private boolean[] byteArray2BitArray(byte[] bytes) {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length * 8; i++) {
            if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
                bits[i] = true;
        }
        return bits;
    }

    private int bytes2Int(byte[] bytes) {
        return ((bytes[1] & 0xFF) << 8) | (bytes[0] & 0xFF);
    }
}
