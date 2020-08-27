package day4.CoffeeShop;


import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

public class SauceCaramel extends Decorator{
    Coffee coffee;

    public SauceCaramel(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ",焦糖风味酱";
    }

    @Override
    public Size getSize() {
        return this.coffee.getSize();
    }

    @Override
    public Temperature getTemperature() {
        return this.coffee.getTemperature();
    }

    @Override
    public Milk getMilk() {
        return this.coffee.getMilk();
    }

    @Override
    public double getPrice() {
        return this.coffee.getPrice() + 4;
    }
}