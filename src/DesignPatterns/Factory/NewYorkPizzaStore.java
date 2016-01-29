package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class NewYorkPizzaStore extends PizzaStore
{
    @Override
    public Pizza createPizza(String desc)
    {
        if ( desc.equals("cheese") ) return new NewYorkCheesePizza();
        else return null;
    }
}

