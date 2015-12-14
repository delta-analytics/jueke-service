package deltaanalytics.jueke.hardware.serial;

import java.io.IOException;
import java.io.OutputStream;


public class SerialWriter {
    OutputStream out;

    public SerialWriter(OutputStream out) {
        this.out = out;
    }

    public void run(byte[] cmd) {
        try {

            this.out.write(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
