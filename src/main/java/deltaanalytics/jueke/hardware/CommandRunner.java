package deltaanalytics.jueke.hardware;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.data.repository.JuekeStatusRepository;
import deltaanalytics.jueke.hardware.domain.*;
import deltaanalytics.jueke.hardware.serial.JuekeSerialConnection;
import deltaanalytics.jueke.hardware.serial.JuekeSerialConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

public class CommandRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);
    private OutputStream outputStream;
    private InputStream inputStream;
    private JuekeStatusRepository juekeStatusRepository;

    public CommandRunner(String serialPortName) throws Exception {
        JuekeSerialConnection juekeSerialConnection = new JuekeSerialConnectionFactory().connect(serialPortName);
        inputStream = juekeSerialConnection.getInputStream();
        outputStream = juekeSerialConnection.getOutputStream();
        juekeStatusRepository = new JuekeStatusRepository();
    }

    public void setValves(ValveState valveState1, ValveState valveState2, ValveState valveState3, ValveState valveState4,
                          ValveState valveState5, ValveState valveState6, ValveState valveState7, ValveState valveState8) throws Exception {
        String stateString = valveStatesToString(valveState1, valveState2, valveState3, valveState4,
                valveState5, valveState6, valveState7, valveState8);
        LOGGER.info("setValves for String " + stateString);
        byte parseByte = (byte) Integer.parseInt(stateString, 2);
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_VALVES, parseByte, (byte) 0, (byte) 0, (byte) 0).toByteArray());
    }

    public void disablePump() throws Exception {
        LOGGER.info("disablePump");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER).toByteArray());
    }

    public void setPumpSpeed(int speed) throws Exception {
        LOGGER.info("setPumpSpeed");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER, (byte) speed, (byte) 0, (byte) 0, (byte) 0).toByteArray());
    }

    public void setTemperature(short temperature) throws Exception {
        short xt = (short) (temperature * 100);
        LOGGER.info("setTemperature");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_TEMP_HEATER, (byte) (xt & 0xff), (byte) ((xt >> 8) & 0xff), (byte) 0, (byte) 0).toByteArray());
    }

    public void setPressure(short pressure) throws Exception {
        LOGGER.info("setPressure");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_TEMP_HEATER, (byte) (pressure & 0xff), (byte) ((pressure >> 8) & 0xff), (byte) 0, (byte) 0).toByteArray());
    }

    public JuekeStatus getStatus() throws Exception {
        JuekeStatus juekeStatus = null;
        LOGGER.info("Start to jueke");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_COM).toByteArray());
        int data;
        int responseLength = 26;
        byte[] buffer = new byte[responseLength];
        int len = 0;
        LOGGER.info("read Status");
        while ((data = inputStream.read()) > -1) {
            buffer[len++] = (byte) data;
            if (len == responseLength) {
                Checksum checkSum = new Checksum();
                if (checkSum.checkForConsistency(buffer)) {
                    juekeStatus = new JuekeStatusFactory().build(buffer);
                    juekeStatusRepository.createOrUpdate(juekeStatus);
                }
                break;
            }
        }
        LOGGER.info("Stop to jueke");
        outputStream.write(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_COM).toByteArray());
        return juekeStatus;
    }

    private String valveStatesToString(ValveState valveState1, ValveState valveState2, ValveState valveState3, ValveState valveState4,
                                       ValveState valveState5, ValveState valveState6, ValveState valveState7, ValveState valveState8) {

        return valveState1.getState() + valveState2.getState() + valveState3.getState() + valveState4.getState() + valveState5.getState() + valveState6.getState() + valveState7.getState() + valveState8.getState();

    }
}
