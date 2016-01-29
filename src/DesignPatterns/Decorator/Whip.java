package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Whip extends CondimentDecorator
{
    public Whip(Beverage beverage)
    {
        super(beverage);
    }

    public Whip(Beverage beverage, Price price)
    {
        super(beverage, price);
    }

    @Override
    public String getDescription()
    {
        return beverage.getDescription() + ", Whip";
    }


}
