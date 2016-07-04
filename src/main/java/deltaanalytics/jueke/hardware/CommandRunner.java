package deltaanalytics.jueke.hardware;

import deltaanalytics.jueke.data.dto.ValveStateDto;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.data.repository.JuekeStatusRepository;
import deltaanalytics.jueke.hardware.domain.JuekeStatusFactory;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellCommandNumber;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellMessage;
import deltaanalytics.jueke.hardware.serial.JuekeSerialConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("production")
@Service
public class CommandRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);
    private JuekeStatusRepository juekeStatusRepository;
    private JuekeSerialConnectionFactory juekeSerialConnectionFactory;

    public void setValve(int valve, String state) throws Exception {
        LOGGER.info("setValve " + valve + " with state " + state);
        int valveStateAsNumber = 0;
        if (!isCorrectValve(valve)) {
            throw new RuntimeException("Valve " + valve + " must be one of [1,2,3,4,5,6,7,8]");
        }
        if (!isCorrectValveState(state)) {
            throw new RuntimeException("Valve State only can be Enable or Disable, " + state + " is not correct.");
        }
        if (state.equalsIgnoreCase("enable")) {
            valveStateAsNumber = 1;
        }
        if (state.equalsIgnoreCase("disable")) {
            valveStateAsNumber = 0;
        }
        JuekeStatus juekeStatus = getStatus();
        ValveStateDto valveStateDto = new ValveStateDto();

        if (valve == 1) {
            valveStateDto.setValveState1(valveStateAsNumber);
        } else {
            valveStateDto.setValveState1(valveStateAsInt(juekeStatus.isValveStatus1()));
        }
        if (valve == 2) {
            valveStateDto.setValveState2(valveStateAsNumber);
        } else {
            valveStateDto.setValveState2(valveStateAsInt(juekeStatus.isValveStatus2()));
        }
        if (valve == 3) {
            valveStateDto.setValveState3(valveStateAsNumber);
        } else {
            valveStateDto.setValveState3(valveStateAsInt(juekeStatus.isValveStatus3()));
        }
        if (valve == 4) {
            valveStateDto.setValveState4(valveStateAsNumber);
        } else {
            valveStateDto.setValveState4(valveStateAsInt(juekeStatus.isValveStatus4()));
        }
        if (valve == 5) {
            valveStateDto.setValveState5(valveStateAsNumber);
        } else {
            valveStateDto.setValveState5(valveStateAsInt(juekeStatus.isValveStatus5()));
        }
        if (valve == 6) {
            valveStateDto.setValveState6(valveStateAsNumber);
        } else {
            valveStateDto.setValveState6(valveStateAsInt(juekeStatus.isValveStatus6()));
        }
        if (valve == 7) {
            valveStateDto.setValveState7(valveStateAsNumber);
        } else {
            valveStateDto.setValveState7(valveStateAsInt(juekeStatus.isValveStatus7()));
        }
        if (valve == 8) {
            valveStateDto.setValveState8(valveStateAsNumber);
        } else {
            valveStateDto.setValveState8(valveStateAsInt(juekeStatus.isValveStatus8()));
        }

        String valveStatesToString = valveStateDto.valveStatesToString();
        LOGGER.info("------------------------------");
        LOGGER.info(valveStatesToString);
        byte parseByte = (byte) Integer.parseInt(valveStatesToString, 2);
        LOGGER.info("parseByte " + parseByte);
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_VALVES, parseByte, (byte) 0, (byte) 0, (byte) 0));
        LOGGER.info("setValve end");
    }

    private int valveStateAsInt(boolean state) {
        if (state) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean isCorrectValve(int valve) {
        return valve >= 1 && valve <= 8;
    }

    private boolean isCorrectValveState(String state) {
        return state.equalsIgnoreCase("Enable") || state.equalsIgnoreCase("Disable");
    }

    public void disablePump() throws Exception {
        LOGGER.info("disablePump");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER));
    }

    public void setPumpSpeed(int speed) throws Exception {
        LOGGER.info("setPumpSpeed");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER, (byte) speed, (byte) 0, (byte) 0, (byte) 0));
    }

    public void setTemperature(int temperature) throws Exception {
        short xt = (short) (temperature * 100);
        LOGGER.info("setTemperature");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_TEMP_HEATER, (byte) (xt & 0xff), (byte) ((xt >> 8) & 0xff), (byte) 0, (byte) 0));
    }

    public void setPressure(int pressure) throws Exception {
        LOGGER.info("setPressure");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PRESSURE_SETPOINT, (byte) (pressure & 0xff), (byte) ((pressure >> 8) & 0xff), (byte) 0, (byte) 0));
    }

    @Scheduled(fixedRate = 1000)
    private void calculateStatus() throws Exception {
        juekeSerialConnectionFactory.getStatus();
    }

    public JuekeStatus getStatus() throws Exception {
        return juekeStatusRepository.findFirst1ByOrderByIdDesc();
    }

    public void startPressureRegulation() throws Exception {
        LOGGER.info("startPressureRegulation");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_PRESS_REGULATION));
    }

    public void stopPressureRegulation() throws Exception {
        LOGGER.info("stopPressureRegulation");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_PRESS_REGULATION));
    }

    public void startTemperatureRegulation() throws Exception {
        LOGGER.info("startTemperatureRegulation");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_HEAT_REGULATION));
    }

    public void stopTemperatureRegulation() throws Exception {
        LOGGER.info("stopTemperatureRegulation");
        juekeSerialConnectionFactory.executeCommand(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_HEAT_REGULATION));
    }

    @Autowired
    public void setJuekeStatusRepository(JuekeStatusRepository juekeStatusRepository) {
        this.juekeStatusRepository = juekeStatusRepository;
    }

    @Autowired
    public void setJuekeSerialConnectionFactory(JuekeSerialConnectionFactory juekeSerialConnectionFactory) {
        this.juekeSerialConnectionFactory = juekeSerialConnectionFactory;
    }

}
