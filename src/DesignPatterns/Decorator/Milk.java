package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Milk extends CondimentDecorator
{
    public Milk(Beverage beverage)
    {
        super(beverage);
    }

    public Milk(Beverage beverage, Price price)
    {
        super(beverage, price);
    }

    @Override
    public String getDescription()
    {
        return beverage.getDescription() + ", Milk";
    }
}
