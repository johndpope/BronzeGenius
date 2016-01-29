package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public abstract class CondimentDecorator extends Beverage
{
    Beverage beverage;
    Price price;

    public CondimentDecorator(Beverage beverage)
    {
        this.beverage = beverage;
        price = new Price();
    }

    public CondimentDecorator(Beverage beverage,Price price)
    {
        this.beverage = beverage;
        this.price = price;
    }

    @Override
    public float cost()
    {
        return beverage.cost() + price.getPrice();
    }

    public abstract String getDescription();
}
