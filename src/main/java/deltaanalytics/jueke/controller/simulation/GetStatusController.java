package deltaanalytics.jueke.controller.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("simulation")
@RestController
public class GetStatusController {
    private Logger LOGGER = LoggerFactory.getLogger(GetStatusController.class);
    private SimulatedJuekeStatus simulatedJuekeStatus;

    @RequestMapping("/status")
    public SimulatedJuekeStatus getStatus() {
        LOGGER.info("getStatus");
        return simulatedJuekeStatus;
    }

    @Autowired
    public void setSimulatedJuekeStatus(SimulatedJuekeStatus simulatedJuekeStatus) {
        this.simulatedJuekeStatus = simulatedJuekeStatus;
    }
}
