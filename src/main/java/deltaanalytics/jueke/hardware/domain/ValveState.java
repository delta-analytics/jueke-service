package deltaanalytics.jueke.hardware.domain;

public enum ValveState {
    ACTIVE("1"), INACTIVE("0");

    private final String state;

    ValveState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
