package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Price
{
    private float price;
    public Price(float price)
    {
        this.price = price;
    }

    public Price()
    {
        this.price = 0;
    }

    public float getPrice()
    {
        return price;
    }
}
