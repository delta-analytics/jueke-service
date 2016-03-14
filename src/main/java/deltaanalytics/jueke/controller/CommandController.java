package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.data.ValveStateDto;
import deltaanalytics.jueke.hardware.CommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {
    private Logger LOGGER = LoggerFactory.getLogger(CommandController.class);
    private CommandRunner commandRunner;

    @RequestMapping(value = "/pump/disable", method = RequestMethod.POST)
    public void disablePump() {
        LOGGER.info("disablePump");
        try {
            commandRunner.disablePump();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/pump/speed/{speed}", method = RequestMethod.POST)
    public void setPumpSpeed(@PathVariable int speed) {
        LOGGER.info("setPumpSpeed " + speed);
        try {
            commandRunner.setPumpSpeed(speed);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/temperature/{temperature}", method = RequestMethod.POST)
    public void setTemperature(@PathVariable short temperature) {
        LOGGER.info("setTemperature " + temperature);
        try {
            commandRunner.setTemperature(temperature);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/pressure/regulation/start", method = RequestMethod.POST)
    public void startPressureRegulation() {
        LOGGER.info("startPressureRegulation ");
        try {
            commandRunner.startPressureRegulation();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/pressure/regulation/stop", method = RequestMethod.POST)
    public void stopPressureRegulation() {
        LOGGER.info("stopPressureRegulation ");
        try {
            commandRunner.stopPressureRegulation();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/temperature/regulation/start", method = RequestMethod.POST)
    public void startTemperatureRegulation() {
        LOGGER.info("startTemperatureRegulation ");
        try {
            commandRunner.startTemperatureRegulation();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/temperature/regulation/stop", method = RequestMethod.POST)
    public void stopTemperatureRegulation() {
        LOGGER.info("stopTemperatureRegulation ");
        try {
            commandRunner.stopTemperatureRegulation();
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/pressure/{pressure}", method = RequestMethod.POST)
    public void setPressure(@PathVariable short pressure) {
        LOGGER.info("setPressure " + pressure);
        try {
            commandRunner.setPressure(pressure);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @RequestMapping(value = "/valves/{valveDto}", method = RequestMethod.POST)
    public void setValves(@RequestParam ValveStateDto valveStateDto) {
        LOGGER.info("setValves " + valveStateDto);
        try {
            commandRunner.setValves(valveStateDto);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @Autowired
    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }
}
