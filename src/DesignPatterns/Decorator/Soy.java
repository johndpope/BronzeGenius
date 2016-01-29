package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Soy extends CondimentDecorator
{
    public Soy(Beverage beverage)
    {
        super(beverage);
    }

    public Soy(Beverage beverage, Price price)
    {
        super(beverage, price);
    }

    @Override
    public String getDescription()
    {
        return beverage.getDescription() + ", Soy";
    }

}
