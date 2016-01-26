package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class MuteQuack implements QuackBehavior
{
    public void quack()
    {
        System.out.println("Silence");
    }
}
