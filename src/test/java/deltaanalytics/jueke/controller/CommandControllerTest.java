package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.data.ValveStateDto;
import deltaanalytics.jueke.hardware.CommandRunner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandControllerTest {
    private CommandRunner commandRunner;
    private CommandController commandController;

    @Before
    public void setUp(){
        commandController = new CommandController();
        commandRunner = mock(CommandRunner.class);
        commandController.setCommandRunner(commandRunner);
    }

    @Test
    public void disablePump() throws Exception{
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
        short temperature = (short) 10;
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
        short pressure = (short) 10;
        commandController.setPressure(pressure);
        verify(commandRunner).setPressure(pressure);
    }

    @Test
    public void setValves() throws Exception {
        ValveStateDto valveStateDto = new ValveStateDto();
        valveStateDto.setValveState1(0);
        valveStateDto.setValveState2(1);
        valveStateDto.setValveState3(1);
        valveStateDto.setValveState4(0);
        valveStateDto.setValveState5(0);
        valveStateDto.setValveState6(0);
        valveStateDto.setValveState7(1);
        valveStateDto.setValveState8(1);
        commandController.setValves(valveStateDto);
        verify(commandRunner).setValves(valveStateDto);
    }
}