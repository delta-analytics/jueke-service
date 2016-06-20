package deltaanalytics.jueke.data.service;

import deltaanalytics.jueke.data.dto.JuekeMathParametersDto;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AverageTempAndPressureCalculatorTest {

    @Test
    public void run(){
        JuekeStatus juekeStatus1 = new JuekeStatus();
        juekeStatus1.setActualTempHeater(20);
        juekeStatus1.setActualPressureCell(20);

        JuekeStatus juekeStatus2 = new JuekeStatus();
        juekeStatus2.setActualTempHeater(30.2);
        juekeStatus2.setActualPressureCell(40);
        List<JuekeStatus> juekeStatuses = new ArrayList<>(2);
        juekeStatuses.add(juekeStatus1);
        juekeStatuses.add(juekeStatus2);

        JuekeMathParametersDto juekeMathParametersDto = new AverageTempAndPressureCalculator().run(juekeStatuses);

        assertThat(juekeMathParametersDto.getTemp(), is(25.1));
        assertThat(juekeMathParametersDto.getpAtm(), is(30.0));
    }
}