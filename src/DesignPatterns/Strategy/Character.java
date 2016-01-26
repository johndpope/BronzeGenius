package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public abstract class Character
{
    WeaponBehavior weapon;
    public void fight()
    {
        weapon.useWeapon();
    }

    public void setWeapon(WeaponBehavior weapon)
    {
        this.weapon = weapon;
    }
}
