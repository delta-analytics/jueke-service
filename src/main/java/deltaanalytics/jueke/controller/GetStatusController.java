package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.hardware.CommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("production")
@RestController
public class GetStatusController {
    private Logger LOGGER = LoggerFactory.getLogger(GetStatusController.class);
    private CommandRunner commandRunner;

    @RequestMapping("/status")
    public JuekeStatus getStatus() {
        JuekeStatus result;
        try {
            result = commandRunner.getStatus();
        } catch (Exception e) {
            LOGGER.error("", e);
            result = new JuekeStatus();
        }
        return result;
    }

    @Autowired
    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }
}
