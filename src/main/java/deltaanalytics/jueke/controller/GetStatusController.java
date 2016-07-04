package deltaanalytics.jueke.controller;

import deltaanalytics.jueke.data.dto.JuekeMathParametersDto;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import deltaanalytics.jueke.data.repository.JuekeStatusRepository;
import deltaanalytics.jueke.data.service.AverageTempAndPressureCalculator;
import deltaanalytics.jueke.hardware.CommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Profile("production")
@RestController
public class GetStatusController {
    private Logger LOGGER = LoggerFactory.getLogger(GetStatusController.class);
    private CommandRunner commandRunner;
    @Autowired
    private JuekeStatusRepository juekeStatusRepository;
    @Autowired
    private AverageTempAndPressureCalculator averageTempAndPressureCalculator;

    @RequestMapping("/status")
    public JuekeStatus getStatus() {
        LOGGER.info("getStatus");
        JuekeStatus result;
        try {
            result = commandRunner.getStatus();
        } catch (Exception e) {
            LOGGER.error("", e);
            result = new JuekeStatus();
        }
        return result;
    }

    @RequestMapping("/statuses")
    public List<JuekeStatus> getStatuses() {
        return juekeStatusRepository.findAll();
    }

    @RequestMapping(value = "/statusMiddled")
    public JuekeMathParametersDto fetchResult(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
                                              @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate) {
        LOGGER.info("statusMiddled " + startDate + " <-> " + endDate);
        List<JuekeStatus> juekeStatuses = juekeStatusRepository.findByStatusDateTimeBetween(startDate, endDate);
        LOGGER.info("juekeStatuses between count " + juekeStatuses.size());
        JuekeMathParametersDto result = averageTempAndPressureCalculator.run(juekeStatuses);
        LOGGER.info("average ActualTempHeater=" + result.getTemp());
        LOGGER.info("average ActualPressureCell=" + result.getpAtm());
        result.setStartDateTime(startDate);
        result.setEndDateTime(endDate);
        return result;
    }

    @Autowired
    public void setCommandRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }
}
