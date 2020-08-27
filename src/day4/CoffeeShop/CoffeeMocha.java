package day4.CoffeeShop;

/**
 *    具体构件类
 */
public class CoffeeMocha extends Coffee{



    public CoffeeMocha(){
        description = "摩卡咖啡";
    }

    @Override
    public double getPrice() {
        switch (getSize()){
            case SMALL:return 31;
            case MEDIUM:return 34;
            case LARGE:return 36;
            default:return 31;
        }
    }

}