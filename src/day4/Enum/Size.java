package day4.Enum;

public enum Size {
    SMALL,MEDIUM,LARGE;

    public String toString() {
        switch(this) {
            case SMALL: return "中杯";
            case MEDIUM: return "大杯";
            case LARGE: return "超大杯";
            default:       return "unspecified";
        }
    }
}
