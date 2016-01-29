package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Sugar extends CondimentDecorator
{
    public Sugar(Beverage beverage)
    {
        super(beverage);
    }

    public Sugar(Beverage beverage, Price price)
    {
        super(beverage, price);
    }

    @Override
    public String getDescription()
    {
        return beverage.getDescription() + ", Sugar";
    }

}
