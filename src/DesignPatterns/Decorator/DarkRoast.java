package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public class DarkRoast extends Beverage
{
    public DarkRoast()
    {
        description = "DarkRoast";
    }

    @Override
    public float cost()
    {
        return 1.99f;
    }
}
