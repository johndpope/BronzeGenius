package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class ChicagoPizzaStore extends PizzaStore
{
    @Override
    public Pizza createPizza(String desc)
    {
        if ( desc.equals("clam") ) return new ChicagoClamPizza();
        else if ( desc.equals("pepperoni") ) return new ChicagoPepperoniPizza();
        else return null;
    }
}
