package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class Quack implements QuackBehavior
{
    @Override
    public void quack()
    {
        System.out.println("Quack");
    }
}
