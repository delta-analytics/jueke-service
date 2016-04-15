package deltaanalytics.jueke.controller.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("simulation")
@RestController
public class CommandController {
    private Logger LOGGER = LoggerFactory.getLogger(CommandController.class);
    private SimulatedJuekeStatus simulatedJuekeStatus;

    @RequestMapping(value = "/pump/disable", method = RequestMethod.POST)
    public void disablePump() {
        LOGGER.info("disablePump");
        simulatedJuekeStatus.setStatusOfPump(false);
    }

    @RequestMapping(value = "/pump/speed/{speed}", method = RequestMethod.POST)
    public void setPumpSpeed(@PathVariable int speed) {
        LOGGER.info("setPumpSpeed " + speed);
        simulatedJuekeStatus.setStatusOfPump(true);
        simulatedJuekeStatus.setPumpPower(speed);
    }

    @RequestMapping(value = "/temperature/{temperature}", method = RequestMethod.POST)
    public void setTemperature(@PathVariable int temperature) {
        LOGGER.info("setTemperature " + temperature);
        simulatedJuekeStatus.setActualTempHeater(temperature);
    }

    @RequestMapping(value = "/pressure/regulation/start", method = RequestMethod.POST)
    public void startPressureRegulation() {
        LOGGER.info("startPressureRegulation ");
        simulatedJuekeStatus.setPressureRegulationActive(true);
    }

    @RequestMapping(value = "/pressure/regulation/stop", method = RequestMethod.POST)
    public void stopPressureRegulation() {
        LOGGER.info("stopPressureRegulation ");
        simulatedJuekeStatus.setPressureRegulationActive(false);
    }

    @RequestMapping(value = "/temperature/regulation/start", method = RequestMethod.POST)
    public void startTemperatureRegulation() {
        LOGGER.info("startTemperatureRegulation ");
        simulatedJuekeStatus.setHeaterRegulationActive(true);
    }

    @RequestMapping(value = "/temperature/regulation/stop", method = RequestMethod.POST)
    public void stopTemperatureRegulation() {
        LOGGER.info("stopTemperatureRegulation ");
        simulatedJuekeStatus.setHeaterRegulationActive(false);
    }

    @RequestMapping(value = "/pressure/{pressure}", method = RequestMethod.POST)
    public void setPressure(@PathVariable int pressure) {
        LOGGER.info("setPressure " + pressure);
        simulatedJuekeStatus.setActualPressureCell(pressure);
    }

    @RequestMapping(value = "/valves/{valve}", method = RequestMethod.POST)
    public void setValve(@PathVariable int valve, @RequestParam("state") String state) {
        LOGGER.info("setValve " + valve);
        boolean valveStateAsBoolean = false;
        if (state.equalsIgnoreCase("enable")) {
            valveStateAsBoolean = true;
        }
        if (state.equalsIgnoreCase("disable")) {
            valveStateAsBoolean = false;
        }
        if (valve == 1) {
            simulatedJuekeStatus.setValveStatus1(valveStateAsBoolean);
        }
        if (valve == 2) {
            simulatedJuekeStatus.setValveStatus2(valveStateAsBoolean);
        }
        if (valve == 3) {
            simulatedJuekeStatus.setValveStatus3(valveStateAsBoolean);
        }
        if (valve == 4) {
            simulatedJuekeStatus.setValveStatus4(valveStateAsBoolean);
        }
        if (valve == 5) {
            simulatedJuekeStatus.setValveStatus5(valveStateAsBoolean);
        }
        if (valve == 6) {
            simulatedJuekeStatus.setValveStatus6(valveStateAsBoolean);
        }
        if (valve == 7) {
            simulatedJuekeStatus.setValveStatus7(valveStateAsBoolean);
        }
        if (valve == 8) {
            simulatedJuekeStatus.setValveStatus8(valveStateAsBoolean);
        }

    }

    @Autowired
    public void setSimulatedJuekeStatus(SimulatedJuekeStatus simulatedJuekeStatus) {
        this.simulatedJuekeStatus = simulatedJuekeStatus;
    }
}
