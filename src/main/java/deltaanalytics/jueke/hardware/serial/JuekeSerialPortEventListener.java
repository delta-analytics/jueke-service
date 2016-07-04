package deltaanalytics.jueke.hardware.serial;

import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.data.repository.JuekeStatusRepository;
import deltaanalytics.jueke.hardware.domain.Checksum;
import deltaanalytics.jueke.hardware.domain.JuekeStatusFactory;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JuekeSerialPortEventListener implements SerialPortEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(JuekeSerialConnectionFactory.class);
    private InputStream in;
    @Autowired
    private JuekeStatusRepository juekeStatusRepository;
    @Autowired
    private JuekeStatusFactory juekeStatusFactory;

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                int data;
                byte[] buffer = new byte[26];
                int len = 0;
                LOGGER.info("read Response");
                try {
                    while ((data = in.read()) > -1) {
                        buffer[len++] = (byte) data;
                        if (len == 26) {
                            Checksum checkSum = new Checksum();
                            if (checkSum.checkForConsistency(buffer)) {
                                LOGGER.info("Checksum Ok");
                            } else {
                                throw new RuntimeException("Checksum wrong");
                            }
                            break;
                        }
                    }
                    JuekeStatus juekeStatus = juekeStatusFactory.build(buffer);
                    LOGGER.info(juekeStatus.toString());
                    juekeStatusRepository.save(juekeStatus);

                } catch (IOException e) {
                    LOGGER.error("", e);
                }
                break;
        }
    }

    public void setIn(InputStream in) {
        this.in = in;
    }
}
