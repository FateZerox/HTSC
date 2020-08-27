package day4;

import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

public class SyrupOriginal extends Decorator{

    Coffee coffee;

    public SyrupOriginal(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ",原味糖浆";
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