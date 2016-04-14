package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.hardware.CommandRunner;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandControllerTest {
    private CommandRunner commandRunner;
    private CommandController commandController;

    @Before
    public void setUp() {
        commandController = new CommandController();
        commandRunner = mock(CommandRunner.class);
        commandController.setCommandRunner(commandRunner);
    }

    @Test
    public void disablePump() throws Exception {
        commandController.disablePump();
        verify(commandRunner).disablePump();
    }

    @Test
    public void setPumpSpeed() throws Exception {
        int speed = 10;
        commandController.setPumpSpeed(speed);
        verify(commandRunner).setPumpSpeed(speed);
    }

    @Test
    public void setTemperature() throws Exception {
        int temperature = 10;
        commandController.setTemperature(temperature);
        verify(commandRunner).setTemperature(temperature);
    }

    @Test
    public void startPressureRegulation() throws Exception {
        commandController.startPressureRegulation();
        verify(commandRunner).startPressureRegulation();
    }

    @Test
    public void stopPressureRegulation() throws Exception {
        commandController.stopPressureRegulation();
        verify(commandRunner).stopPressureRegulation();
    }

    @Test
    public void startTemperatureRegulation() throws Exception {
        commandController.startTemperatureRegulation();
        verify(commandRunner).startTemperatureRegulation();
    }

    @Test
    public void stopTemperatureRegulation() throws Exception {
        commandController.stopTemperatureRegulation();
        verify(commandRunner).stopTemperatureRegulation();
    }

    @Test
    public void setPressure() throws Exception {
        int pressure = 10;
        commandController.setPressure(pressure);
        verify(commandRunner).setPressure(pressure);
    }

    @Test
    public void setValve() throws Exception {
        commandController.setValve(1, "Enable");
        verify(commandRunner).setValve(1, "Enable");
    }
}