package deltaanalytics.jueke.data.dto;

import java.time.LocalDateTime;

public class JuekeMathParametersDto {

    private double temp;
    private double pAtm;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getpAtm() {
        return pAtm;
    }

    public void setpAtm(double pAtm) {
        this.pAtm = pAtm;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "JuekeMathParametersDto{" +
                "temp=" + temp +
                ", pAtm=" + pAtm +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}