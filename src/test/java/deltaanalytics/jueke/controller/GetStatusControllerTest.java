package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.hardware.CommandRunner;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetStatusControllerTest {

    @Test
    public void getStatus() throws Exception {
        GetStatusController getStatusController = new GetStatusController();
        CommandRunner commandRunner = mock(CommandRunner.class);
        JuekeStatus juekeStatus = build();
        when(commandRunner.getStatus()).thenReturn(juekeStatus);
        getStatusController.setCommandRunner(commandRunner);

        JuekeStatus result = getStatusController.getStatus();

        verify(commandRunner).getStatus();
        assertThat(result, is(equalTo(juekeStatus)));
    }

    private JuekeStatus build(){
        JuekeStatus juekeStatus = new JuekeStatus();
        juekeStatus.setStatusDateTime(LocalDateTime.now());
        juekeStatus.setActualPressureCell(1);
        juekeStatus.setRawJuekeMessage(new byte[0]);
        juekeStatus.setPowerHeater((byte) 1);
        return juekeStatus;
    }
}