package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class FlyNoWay implements FlyBehavior
{
    @Override
    public void fly() {
        System.out.println("I cannot fly");
    }
}
