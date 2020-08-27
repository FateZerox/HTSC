package day4;

import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

public class SauceMocha extends Decorator{
    Coffee coffee;

    public SauceMocha(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ",摩卡淋酱";
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
