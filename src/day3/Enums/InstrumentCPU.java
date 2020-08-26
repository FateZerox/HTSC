package day3.Enums;

public enum InstrumentCPU {
    //处理器

    INTER,RALON;

    public String toString() {
        switch(this) {
            case INTER:   return "因特尔";
            case RALON:    return "锐龙";
            default:       return "Unspecified";
        }
    }
}
