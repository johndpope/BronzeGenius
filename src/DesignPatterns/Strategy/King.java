package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class King extends Character
{
    public King()
    {
        weapon = new SwordWeaponBehavior();
    }
}
