package deltaanalytics.jueke.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class JuekeStatus {
    private long id;
    private LocalDateTime statusDateTime;
    private byte start;
    private byte numberOfBytes;
    private byte controllerStatus;
    private boolean statusOfPump;
    private boolean pressureRegulationActive;
    private boolean temperatureOfHeaterOk;
    private boolean heaterRegulationActive;
    private byte errorFlags;
    private int errorCode;
    private boolean valveStatus1;
    private boolean valveStatus2;
    private boolean valveStatus3;
    private boolean valveStatus4;
    private boolean valveStatus5;
    private boolean valveStatus6;
    private boolean valveStatus7;
    private boolean valveStatus8;
    private byte powerHeater;
    private double actualTempHeater;
    private double setpointHeater;
    private double actualPressureCell;
    private double setpointPressure;
    private double pumpPower;
    private double tempPT100_1;
    private double tempPT100_2;
    private byte counter;
    private byte end;
    private int crc;

    private byte[] rawJuekeMessage;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(LocalDateTime statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public byte getStart() {
        return start;
    }

    public void setStart(byte start) {
        this.start = start;
    }

    public byte getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(byte numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }

    public byte getControllerStatus() {
        return controllerStatus;
    }

    public void setControllerStatus(byte controllerStatus) {
        this.controllerStatus = controllerStatus;
    }

    public boolean isStatusOfPump() {
        return statusOfPump;
    }

    public void setStatusOfPump(boolean statusOfPump) {
        this.statusOfPump = statusOfPump;
    }

    public boolean isPressureRegulationActive() {
        return pressureRegulationActive;
    }

    public void setPressureRegulationActive(boolean pressureRegulationActive) {
        this.pressureRegulationActive = pressureRegulationActive;
    }

    public boolean isTemperatureOfHeaterOk() {
        return temperatureOfHeaterOk;
    }

    public void setTemperatureOfHeaterOk(boolean temperatureOfHeaterOk) {
        this.temperatureOfHeaterOk = temperatureOfHeaterOk;
    }

    public boolean isHeaterRegulationActive() {
        return heaterRegulationActive;
    }

    public void setHeaterRegulationActive(boolean heaterRegulationActive) {
        this.heaterRegulationActive = heaterRegulationActive;
    }

    public byte getErrorFlags() {
        return errorFlags;
    }

    public void setErrorFlags(byte errorFlags) {
        this.errorFlags = errorFlags;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isValveStatus1() {
        return valveStatus1;
    }

    public void setValveStatus1(boolean valveStatus1) {
        this.valveStatus1 = valveStatus1;
    }

    public boolean isValveStatus2() {
        return valveStatus2;
    }

    public void setValveStatus2(boolean valveStatus2) {
        this.valveStatus2 = valveStatus2;
    }

    public boolean isValveStatus3() {
        return valveStatus3;
    }

    public void setValveStatus3(boolean valveStatus3) {
        this.valveStatus3 = valveStatus3;
    }

    public boolean isValveStatus4() {
        return valveStatus4;
    }

    public void setValveStatus4(boolean valveStatus4) {
        this.valveStatus4 = valveStatus4;
    }

    public boolean isValveStatus5() {
        return valveStatus5;
    }

    public void setValveStatus5(boolean valveStatus5) {
        this.valveStatus5 = valveStatus5;
    }

    public boolean isValveStatus6() {
        return valveStatus6;
    }

    public void setValveStatus6(boolean valveStatus6) {
        this.valveStatus6 = valveStatus6;
    }

    public boolean isValveStatus7() {
        return valveStatus7;
    }

    public void setValveStatus7(boolean valveStatus7) {
        this.valveStatus7 = valveStatus7;
    }

    public boolean isValveStatus8() {
        return valveStatus8;
    }

    public void setValveStatus8(boolean valveStatus8) {
        this.valveStatus8 = valveStatus8;
    }

    public byte getPowerHeater() {
        return powerHeater;
    }

    public void setPowerHeater(byte powerHeater) {
        this.powerHeater = powerHeater;
    }

    public double getActualTempHeater() {
        return actualTempHeater;
    }

    public void setActualTempHeater(double actualTempHeater) {
        this.actualTempHeater = actualTempHeater;
    }

    public double getSetpointHeater() {
        return setpointHeater;
    }

    public void setSetpointHeater(double setpointHeater) {
        this.setpointHeater = setpointHeater;
    }

    public double getActualPressureCell() {
        return actualPressureCell;
    }

    public void setActualPressureCell(double actualPressureCell) {
        this.actualPressureCell = actualPressureCell;
    }

    public double getSetpointPressure() {
        return setpointPressure;
    }

    public void setSetpointPressure(double setpointPressure) {
        this.setpointPressure = setpointPressure;
    }

    public double getPumpPower() {
        return pumpPower;
    }

    public void setPumpPower(double pumpPower) {
        this.pumpPower = pumpPower;
    }

    public double getTempPT100_1() {
        return tempPT100_1;
    }

    public void setTempPT100_1(double tempPT100_1) {
        this.tempPT100_1 = tempPT100_1;
    }

    public double getTempPT100_2() {
        return tempPT100_2;
    }

    public void setTempPT100_2(double tempPT100_2) {
        this.tempPT100_2 = tempPT100_2;
    }

    public byte getCounter() {
        return counter;
    }

    public void setCounter(byte counter) {
        this.counter = counter;
    }

    public byte getEnd() {
        return end;
    }

    public void setEnd(byte end) {
        this.end = end;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte[] getRawJuekeMessage() {
        return rawJuekeMessage;
    }

    public void setRawJuekeMessage(byte[] rawJuekeMessage) {
        this.rawJuekeMessage = rawJuekeMessage;
    }

    @PrePersist
    public void prePersist() {
        setStatusDateTime(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JuekeStatus that = (JuekeStatus) o;

        if (id != that.id) return false;
        if (start != that.start) return false;
        if (numberOfBytes != that.numberOfBytes) return false;
        if (controllerStatus != that.controllerStatus) return false;
        if (statusOfPump != that.statusOfPump) return false;
        if (pressureRegulationActive != that.pressureRegulationActive) return false;
        if (temperatureOfHeaterOk != that.temperatureOfHeaterOk) return false;
        if (heaterRegulationActive != that.heaterRegulationActive) return false;
        if (errorFlags != that.errorFlags) return false;
        if (errorCode != that.errorCode) return false;
        if (valveStatus1 != that.valveStatus1) return false;
        if (valveStatus2 != that.valveStatus2) return false;
        if (valveStatus3 != that.valveStatus3) return false;
        if (valveStatus4 != that.valveStatus4) return false;
        if (valveStatus5 != that.valveStatus5) return false;
        if (valveStatus6 != that.valveStatus6) return false;
        if (valveStatus7 != that.valveStatus7) return false;
        if (valveStatus8 != that.valveStatus8) return false;
        if (powerHeater != that.powerHeater) return false;
        if (Double.compare(that.actualTempHeater, actualTempHeater) != 0) return false;
        if (Double.compare(that.setpointHeater, setpointHeater) != 0) return false;
        if (Double.compare(that.actualPressureCell, actualPressureCell) != 0) return false;
        if (Double.compare(that.setpointPressure, setpointPressure) != 0) return false;
        if (Double.compare(that.pumpPower, pumpPower) != 0) return false;
        if (Double.compare(that.tempPT100_1, tempPT100_1) != 0) return false;
        if (Double.compare(that.tempPT100_2, tempPT100_2) != 0) return false;
        if (counter != that.counter) return false;
        if (end != that.end) return false;
        if (crc != that.crc) return false;

        return Arrays.equals(rawJuekeMessage, that.rawJuekeMessage);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) start;
        result = 31 * result + (int) numberOfBytes;
        result = 31 * result + (int) controllerStatus;
        result = 31 * result + (statusOfPump ? 1 : 0);
        result = 31 * result + (pressureRegulationActive ? 1 : 0);
        result = 31 * result + (temperatureOfHeaterOk ? 1 : 0);
        result = 31 * result + (heaterRegulationActive ? 1 : 0);
        result = 31 * result + (int) errorFlags;
        result = 31 * result + errorCode;
        result = 31 * result + (valveStatus1 ? 1 : 0);
        result = 31 * result + (valveStatus2 ? 1 : 0);
        result = 31 * result + (valveStatus3 ? 1 : 0);
        result = 31 * result + (valveStatus4 ? 1 : 0);
        result = 31 * result + (valveStatus5 ? 1 : 0);
        result = 31 * result + (valveStatus6 ? 1 : 0);
        result = 31 * result + (valveStatus7 ? 1 : 0);
        result = 31 * result + (valveStatus8 ? 1 : 0);
        result = 31 * result + (int) powerHeater;
        temp = Double.doubleToLongBits(actualTempHeater);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(setpointHeater);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(actualPressureCell);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(setpointPressure);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pumpPower);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempPT100_1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempPT100_2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) counter;
        result = 31 * result + (int) end;
        result = 31 * result + crc;
        result = 31 * result + Arrays.hashCode(rawJuekeMessage);
        return result;
    }

    @Override
    public String toString() {
        return "JuekeStatus{" +
                "id=" + id +
                ", statusDateTime=" + statusDateTime +
                ", start=" + start +
                ", numberOfBytes=" + numberOfBytes +
                ", controllerStatus=" + controllerStatus +
                ", statusOfPump=" + statusOfPump +
                ", pressureRegulationActive=" + pressureRegulationActive +
                ", temperatureOfHeaterOk=" + temperatureOfHeaterOk +
                ", heaterRegulationActive=" + heaterRegulationActive +
                ", errorFlags=" + errorFlags +
                ", errorCode=" + errorCode +
                ", valveStatus1=" + valveStatus1 +
                ", valveStatus2=" + valveStatus2 +
                ", valveStatus3=" + valveStatus3 +
                ", valveStatus4=" + valveStatus4 +
                ", valveStatus5=" + valveStatus5 +
                ", valveStatus6=" + valveStatus6 +
                ", valveStatus7=" + valveStatus7 +
                ", valveStatus8=" + valveStatus8 +
                ", powerHeater=" + powerHeater +
                ", actualTempHeater=" + actualTempHeater +
                ", setpointHeater=" + setpointHeater +
                ", actualPressureCell=" + actualPressureCell +
                ", setpointPressure=" + setpointPressure +
                ", pumpPower=" + pumpPower +
                ", tempPT100_1=" + tempPT100_1 +
                ", tempPT100_2=" + tempPT100_2 +
                ", counter=" + counter +
                ", end=" + end +
                ", crc=" + crc +
                ", rawJuekeMessage=" + Arrays.toString(rawJuekeMessage) +
                '}';
    }
}
