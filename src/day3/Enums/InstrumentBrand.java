package day3.Enums;

public enum InstrumentBrand {
    // 电脑品牌枚举

    THINKPAD, APPLE, LENOVO,HUAWEI;

    public String toString() {
        switch(this) {
            case THINKPAD: return "thinkpad";
            case APPLE: return "apple";
            case LENOVO: return "lenovo";
            case HUAWEI: return "huawei";
            default:       return "unspecified";
        }
    }
}
