package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class Espresso extends Beverage
{
    public Espresso()
    {
        description = "Espresso";
    }

    @Override
    public float cost()
    {
        return 1.39f;
    }
}
