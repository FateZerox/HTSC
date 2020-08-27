package day4.CoffeeShop;

import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

public class SyrupVanilla extends Decorator{
    Coffee coffee;

    public SyrupVanilla(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ",香草糖浆";
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
        return this.coffee.getPrice() + 3;
    }
}
