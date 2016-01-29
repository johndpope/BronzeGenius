package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Mocha extends CondimentDecorator
{
    public Mocha(Beverage beverage)
    {
        super(beverage);
    }

    public Mocha(Beverage beverage,Price price)
    {
        super(beverage, price);
    }

    @Override
    public String getDescription()
    {
        return beverage.getDescription() + ", Mocha";
    }
}
