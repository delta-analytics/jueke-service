package deltaanalytics.jueke;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ByteParseTest {
    //byte als vorzeichenlosen Datentyp nutzen
    //http://openbook.rheinwerk-verlag.de/javainsel/javainsel_18_001.html -> 18.1.5
    @Test
    public void convertStringWithLength8Radix2() {
        byte byte255 = (byte) 255;
        int parsedInt = Integer.parseInt("11111111", 2);

        assertThat(255, is(equalTo(parsedInt)));
        assertThat((byte) -1, is(equalTo(byte255)));
        assertThat(0xff, is(equalTo(byte255 & 0xff)));
    }
}
