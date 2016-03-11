package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.hardware.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {
    private CommandRunner commandRunner;

    @Autowired
    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }
}
