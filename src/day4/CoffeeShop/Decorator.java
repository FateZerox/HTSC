package day4.CoffeeShop;

/**
 *    抽象装饰者类，所有的具体装饰者都必须继承这个类
 */
public abstract class Decorator extends Coffee{
    Coffee coffee;

    public abstract String getDescription();

}