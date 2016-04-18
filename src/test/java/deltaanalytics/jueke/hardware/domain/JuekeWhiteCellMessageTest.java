package deltaanalytics.jueke.hardware.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class JuekeWhiteCellMessageTest {
    @Test
    public void startCom() {
        JuekeWhiteCellMessage juekeWhiteCellMessage = new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.START_COM);

        byte[] expected = new byte[]{(byte) 2, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 3, (byte) 21, (byte) 32};

        assertThat(juekeWhiteCellMessage.toByteArray(), is(equalTo(expected)));
    }

    @Test
    public void stopCom() {
        JuekeWhiteCellMessage juekeWhiteCellMessage = new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.STOP_COM);

        byte[] expected = new byte[]{(byte) 2, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 3, (byte) -37, (byte) -64};

        assertThat(juekeWhiteCellMessage.toByteArray(), is(equalTo(expected)));
    }

    @Test
    public void setValves() {
        JuekeWhiteCellMessage juekeWhiteCellMessage = new JuekeWhiteCellMessage(JuekeWhiteCellCommandNumber.SET_VALVES);

        byte[] expected = new byte[]{(byte) 2, (byte) 4, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 3, (byte) 86, (byte) 33};

        assertThat(juekeWhiteCellMessage.toByteArray(), is(equalTo(expected)));
    }
}
