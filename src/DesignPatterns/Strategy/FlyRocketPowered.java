package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class FlyRocketPowered implements FlyBehavior
{
    @Override
    public void fly()
    {
        System.out.println("I am flying with a rocket");
    }
}
