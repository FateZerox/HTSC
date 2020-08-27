package day4.Enum;

public enum Milk {
    WHOLE_MILK,OAT_MILK,SKIM_MILK,SOYMILK;

    public String toString() {
        switch(this) {
            case WHOLE_MILK: return "全脂牛奶";
            case OAT_MILK: return "燕麦奶";
            case SKIM_MILK: return "脱脂牛奶";
            case SOYMILK: return "豆奶";
            default:       return "unspecified";
        }
    }
}
