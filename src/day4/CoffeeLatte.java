package day4;

import day4.Enum.Size;

/**
 *    具体构件类
 */
public class CoffeeLatte extends Coffee{



    public CoffeeLatte(){
        description = "拿铁咖啡";
    }

    @Override
    public double getPrice() {
        switch (getSize()){
            case SMALL:return 29;
            case MEDIUM:return 32;
            case LARGE:return 34;
            default:return 29;
        }
    }

}