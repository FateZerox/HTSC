package day4.CoffeeShop;

import day4.Enum.Milk;
import day4.Enum.Size;
import day4.Enum.Temperature;

/**
 *    抽象构件类
 */
public abstract class Coffee {

    String description = "抽象的咖啡";
    Size size;
    Temperature temperature;
    Milk milk;

    public String getDescription(){
        return description;
    }

    public abstract double getPrice();

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Milk getMilk() {
        return milk;
    }

    public void setMilk(Milk milk) {
        this.milk = milk;
    }
}