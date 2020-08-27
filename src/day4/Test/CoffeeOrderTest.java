package day4.Test;

import day4.CoffeeShop.*;
import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

import java.util.Scanner;

public class CoffeeOrderTest {
    public static void main(String[] args) {
        System.out.println("欢迎点单～");
        System.out.println("请选择咖啡：1. 美式    2. 拿铁   3.摩卡  （默认拿铁）");
        Scanner scanner = new Scanner(System.in);
        int coffeeIndex = scanner.nextInt();

        Coffee coffee;
        switch (coffeeIndex){
            case 1:
                coffee = new CoffeeAmericano();
                break;
            case 2:
                coffee = new CoffeeLatte();
                break;
            case 3:
                coffee = new CoffeeMocha();
                break;
            default:
                coffee = new CoffeeLatte(); //默认拿铁
                break;
        }

        System.out.println("请选择杯型：1. 中杯    2. 大杯   3.超大杯  （默认中杯）");

        int size = scanner.nextInt();
        switch (size){
            case 1:
                coffee.setSize(Size.SMALL);
                break;
            case 2:
                coffee.setSize(Size.MEDIUM);
                break;
            case 3:
                coffee.setSize(Size.LARGE);
                break;
            default:
                coffee.setSize(Size.SMALL);
                break;
        }

        System.out.println("请选择温度：1.特别热 2.热 3.微热 4.冰 5.少冰 6.去冰 （默认微热）");

        int temperature = scanner.nextInt();
        switch (temperature){
            case 1:
                coffee.setTemperature(Temperature.H_HOT);
                break;
            case 2:
                coffee.setTemperature(Temperature.HOT);
                break;
            case 3:
                coffee.setTemperature(Temperature.M_HOT);
                break;
            case 4:
                coffee.setTemperature(Temperature.ICE);
                break;
            case 5:
                coffee.setTemperature(Temperature.L_ICE);
                break;
            case 6:
                coffee.setTemperature(Temperature.DE_ICE);
                break;
            default:
                coffee.setTemperature(Temperature.M_HOT);
                break;
        }

        System.out.println("请选择牛奶：1.全脂牛奶 2.燕麦奶 3.脱脂牛奶 4.豆奶 （默认全脂牛奶）");

        int milk = scanner.nextInt();
        switch (milk){
            case 1:
                coffee.setMilk(Milk.WHOLE_MILK);
                break;
            case 2:
                coffee.setMilk(Milk.OAT_MILK);
                break;
            case 3:
                coffee.setMilk(Milk.SKIM_MILK);
                break;
            case 4:
                coffee.setMilk(Milk.SOYMILK);
                break;
            default:
                coffee.setMilk(Milk.WHOLE_MILK);
                break;
        }

        boolean flag = true;
        while (flag) {

            System.out.println("请继续添加糖浆加料（3元）：1. 原味糖浆  2. 香草糖浆  3.橡果糖浆  其他键退出（默认不加）");
            int condimentIndex = scanner.nextInt();

            //Coffee condiment;
            switch (condimentIndex) {
                case 1:
                    coffee = new SyrupOriginal(coffee);
                    break;
                case 2:
                    coffee = new SyrupVanilla(coffee);
                    break;
                case 3:
                    coffee = new SyrupAcorn(coffee);
                    break;
                default:
                    flag = false;
                    break;
            }
        }

        flag = true;

        while (flag) {
            System.out.println("请继续添加淋酱加料（4元）：1. 摩卡淋酱  2. 焦糖风味酱  其他键退出（默认不加）");
            int condimentIndex2 = scanner.nextInt();


            switch (condimentIndex2) {
                case 1:
                    coffee = new SauceMocha(coffee);
                    break;
                case 2:
                    coffee = new SauceCaramel(coffee);
                    break;
                default:
                    flag = false;
                    break;
            }
        }

        System.out.println(coffee.getSize()+" "
                + coffee.getTemperature()+" "
                + coffee.getMilk()+" "
                + coffee.getDescription()
                + " 价格是："+ coffee.getPrice());
    }

}
