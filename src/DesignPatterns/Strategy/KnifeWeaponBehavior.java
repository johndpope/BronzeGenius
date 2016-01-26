package DesignPatterns.Strategy;

/**
 * Created by xuch on 2016/1/11.
 */
public class KnifeWeaponBehavior implements WeaponBehavior
{
    @Override
    public void useWeapon()
    {
        System.out.println("Use weapon Knife");
    }
}
