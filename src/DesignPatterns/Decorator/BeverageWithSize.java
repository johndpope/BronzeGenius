package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class BeverageWithSize
{
    Beverage beverage;
    Size size;
    public BeverageWithSize(Beverage beverage, Size size)
    {
        this.beverage = beverage;
        this.size = size;
    }

    public float cost()
    {
        return beverage.cost() * size.getSize();
    }

    public String getDescription()
    {
        return beverage.getDescription();
    }
}
