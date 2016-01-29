package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class HawaiiPizzaStore extends PizzaStore
{
    @Override
    public Pizza createPizza(String desc)
    {
        if ( desc.equals("fruit") ) return new HawaiiFruitPizza();
        else return null;
    }
}
