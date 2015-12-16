package deltaanalytics.jueke.hardware.serial;

import java.io.InputStream;
import java.io.OutputStream;

public class JuekeSerialConnection {
    private OutputStream outputStream;
    private InputStream inputStream;

    public JuekeSerialConnection(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}
