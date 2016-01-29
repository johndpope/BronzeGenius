package DesignPatterns.Decorator;

/**
 * Created by xuch on 2016/1/26.
 */
public abstract class Beverage
{
    String description = "Unknown Beverage";

    public String getDescription()
    {
        return description;
    }

    public abstract float cost();
}
