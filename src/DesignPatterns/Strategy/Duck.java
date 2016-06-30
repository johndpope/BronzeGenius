package DesignPatterns.Strategy;

/**
 * Created by xuch on 4/28/16.
 */
public class Duck
{
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void display()
    {}

    public void performFly()
    {}

    public void performQuack()
    {}

    public void setFlyBehavior(FlyBehavior flyBehavior)
    {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
