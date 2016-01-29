package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class HouseBlend extends Beverage
{
    public HouseBlend()
    {
        description = "HouseBlend";
    }

    @Override
    public float cost()
    {
        return 1.8f;
    }
}
