package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class Queen extends Character
{
    public Queen()
    {
        weapon = new KnifeWeaponBehavior();
    }
}
