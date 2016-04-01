package deltaanalytics.jueke.hardware;

import deltaanalytics.jueke.data.ValveStateDto;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.data.repository.JuekeStatusRepository;
import deltaanalytics.jueke.hardware.domain.JuekeStatusFactory;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellCommandNumber;
import deltaanalytics.jueke.hardware.domain.JuekeWhiteCellMessage;
import deltaanalytics.jueke.hardware.serial.JuekeSerialConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommandRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);
    private JuekeStatusRepository juekeStatusRepository;

    public void setValves(ValveStateDto valveStateDto) throws Exception {

        LOGGER.info("setValves for String " + valveStateDto.valveStatesToString());
        byte parseByte = (byte) Integer.parseInt(valveStateDto.valveStatesToString(), 2);
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_VALVES, parseByte, (byte) 0, (byte) 0, (byte) 0), 0, false);
    }

    public void disablePump() throws Exception {
        LOGGER.info("disablePump");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER), 0, false);
    }

    public void setPumpSpeed(int speed) throws Exception {
        LOGGER.info("setPumpSpeed");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PUMP_POWER, (byte) speed, (byte) 0, (byte) 0, (byte) 0), 0, false);
    }

    public void setTemperature(short temperature) throws Exception {
        short xt = (short) (temperature * 100);
        LOGGER.info("setTemperature");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_TEMP_HEATER, (byte) (xt & 0xff), (byte) ((xt >> 8) & 0xff), (byte) 0, (byte) 0), 0, false);
    }

    public void setPressure(short pressure) throws Exception {
        LOGGER.info("setPressure");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_PRESSURE_SETPOINT, (byte) (pressure & 0xff), (byte) ((pressure >> 8) & 0xff), (byte) 0, (byte) 0), 0, false);
    }

    public JuekeStatus getStatus() throws Exception {
        JuekeStatus juekeStatus = new JuekeStatusFactory().build(JuekeSerialConnectionFactory.execute(null, 26, true));
        juekeStatusRepository.save(juekeStatus);
        return juekeStatus;
    }

    public void startPressureRegulation() throws Exception {
        LOGGER.info("startPressureRegulation");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_PRESS_REGULATION), 0, false);
    }

    public void stopPressureRegulation() throws Exception {
        LOGGER.info("stopPressureRegulation");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_PRESS_REGULATION), 0, false);
    }

    public void startTemperatureRegulation() throws Exception {
        LOGGER.info("startTemperatureRegulation");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_HEAT_REGULATION), 0, false);
    }

    public void stopTemperatureRegulation() throws Exception {
        LOGGER.info("stopTemperatureRegulation");
        JuekeSerialConnectionFactory.execute(new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_HEAT_REGULATION), 0, false);
    }


    @Autowired
    public void setJuekeStatusRepository(JuekeStatusRepository juekeStatusRepository) {
        this.juekeStatusRepository = juekeStatusRepository;
    }


}
