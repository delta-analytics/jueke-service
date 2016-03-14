package deltaanalytics.jueke;

import deltaanalytics.jueke.hardware.serial.JuekeSerialConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Profile("production")
public class ProductionConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductionConfiguration.class);
    private String portName;

    @PostConstruct
    private void establishSerialConnection() {
        JuekeSerialConnectionFactory.establishConnection(portName);
    }

    @Value("${port.name}")
    public void setPortName(String portName) {
        LOGGER.info("port.name = " + portName);
        this.portName = portName;
    }

    @PreDestroy
    private void closeSerialConnection() {
        JuekeSerialConnectionFactory.closeConnection();
    }
}