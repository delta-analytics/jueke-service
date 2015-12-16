package deltaanalytics.jueke.hardware.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JuekeWhiteCellCommandNumberTest {
    @Test
    public void checkNumbers() {
        assertThat(JuekeWhiteCellCommandNumber.START_COM.toByte(), is(equalTo((byte) 1)));
        assertThat(JuekeWhiteCellCommandNumber.STOP_COM.toByte(), is(equalTo((byte) 2)));
        assertThat(JuekeWhiteCellCommandNumber.SET_VALVES.toByte(), is(equalTo((byte) 4)));
        assertThat(JuekeWhiteCellCommandNumber.SET_PUMP_POWER.toByte(), is(equalTo((byte) 5)));
        assertThat(JuekeWhiteCellCommandNumber.SET_TEMP_HEATER.toByte(), is(equalTo((byte) 10)));
        assertThat(JuekeWhiteCellCommandNumber.SET_PRESSURE_SETPOINT.toByte(), is(equalTo((byte) 11)));
    }
}
