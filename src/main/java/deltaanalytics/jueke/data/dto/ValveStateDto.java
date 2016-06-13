package deltaanalytics.jueke.data.dto;

public class ValveStateDto {
    private int valveState1;
    private int valveState2;
    private int valveState3;
    private int valveState4;
    private int valveState5;
    private int valveState6;
    private int valveState7;
    private int valveState8;

    public int getValveState1() {
        return valveState1;
    }

    public void setValveState1(int valveState1) {
        this.valveState1 = valveState1;
    }

    public int getValveState2() {
        return valveState2;
    }

    public void setValveState2(int valveState2) {
        this.valveState2 = valveState2;
    }

    public int getValveState3() {
        return valveState3;
    }

    public void setValveState3(int valveState3) {
        this.valveState3 = valveState3;
    }

    public int getValveState4() {
        return valveState4;
    }

    public void setValveState4(int valveState4) {
        this.valveState4 = valveState4;
    }

    public int getValveState5() {
        return valveState5;
    }

    public void setValveState5(int valveState5) {
        this.valveState5 = valveState5;
    }

    public int getValveState6() {
        return valveState6;
    }

    public void setValveState6(int valveState6) {
        this.valveState6 = valveState6;
    }

    public int getValveState7() {
        return valveState7;
    }

    public void setValveState7(int valveState7) {
        this.valveState7 = valveState7;
    }

    public int getValveState8() {
        return valveState8;
    }

    public void setValveState8(int valveState8) {

    }

    public String valveStatesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(valveState8);
        stringBuilder.append(valveState7);
        stringBuilder.append(valveState6);
        stringBuilder.append(valveState5);
        stringBuilder.append(valveState4);
        stringBuilder.append(valveState3);
        stringBuilder.append(valveState2);
        stringBuilder.append(valveState1);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "ValveStateDto{" +
                "valveState1=" + valveState1 +
                ", valveState2=" + valveState2 +
                ", valveState3=" + valveState3 +
                ", valveState4=" + valveState4 +
                ", valveState5=" + valveState5 +
                ", valveState6=" + valveState6 +
                ", valveState7=" + valveState7 +
                ", valveState8=" + valveState8 +
                '}';
    }
}
