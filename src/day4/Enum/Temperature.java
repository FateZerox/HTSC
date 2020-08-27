package day4.Enum;

public enum Temperature {
    H_HOT,HOT,M_HOT,ICE,L_ICE,DE_ICE;

    public String toString() {
        switch(this) {
            case H_HOT: return "特别热";
            case HOT: return "热";
            case M_HOT: return "微热";
            case ICE: return "冰";
            case L_ICE: return "少冰";
            case DE_ICE: return "去冰";
            default:       return "unspecified";
        }
    }
}
