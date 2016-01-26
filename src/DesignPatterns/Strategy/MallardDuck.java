package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class MallardDuck extends Duck
{
    public MallardDuck()
    {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display()
    {
        System.out.println("I am a real Mallard duck");
    }
}
