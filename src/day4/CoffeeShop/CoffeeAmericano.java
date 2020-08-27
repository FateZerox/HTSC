package day4.CoffeeShop;

/**
 *    具体构件类。美式咖啡
 */
public class CoffeeAmericano extends Coffee{



    public CoffeeAmericano() {
        description = "美式咖啡";
    }

    @Override
    public double getPrice() {
        switch (getSize()){
            case SMALL:return 28;
            case MEDIUM:return 31;
            case LARGE:return 33;
            default:return 28;
        }
    }

}