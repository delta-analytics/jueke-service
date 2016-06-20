package deltaanalytics.jueke.data.service;

import deltaanalytics.jueke.data.dto.JuekeMathParametersDto;
import deltaanalytics.jueke.data.entity.JuekeStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageTempAndPressureCalculator {
    public JuekeMathParametersDto run(List<JuekeStatus> juekeStatusList) {
        JuekeMathParametersDto result = new JuekeMathParametersDto();
        double averageTemp = juekeStatusList
                .stream()
                .mapToDouble(JuekeStatus::getActualTempHeater)
                .average()
                .getAsDouble();
        double averagePressure = juekeStatusList
                .stream()
                .mapToDouble(JuekeStatus::getActualPressureCell)
                .average()
                .getAsDouble();
        result.setTemp(averageTemp);
        result.setpAtm(averagePressure);
        return result;
    }
}
