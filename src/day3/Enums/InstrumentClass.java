package day3.Enums;

public enum InstrumentClass {
    // 电脑类型枚举

    LAPTOP, DESKTOP,SERVER;

    public String toString() {
        switch(this) {
            case LAPTOP: return "笔记本电脑";
            case DESKTOP: return "台式机";
            case SERVER: return "服务器";
            default:       return "unspecified";
        }
    }
}
