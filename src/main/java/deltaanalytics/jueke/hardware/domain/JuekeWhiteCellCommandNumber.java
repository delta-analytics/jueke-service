package deltaanalytics.jueke.hardware.domain;

public enum JuekeWhiteCellCommandNumber {
    START_COM(1), STOP_COM(2), SET_VALVES(4), SET_PUMP_POWER(5), SET_TEMP_HEATER(10), SET_PRESSURE_SETPOINT(11);

    private int command;

    JuekeWhiteCellCommandNumber(int command) {
        this.command = command;
    }

    public byte toByte() {
        return (byte) command;
    }
}
