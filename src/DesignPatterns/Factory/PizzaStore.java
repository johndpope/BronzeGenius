package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public abstract class PizzaStore
{
    public Pizza orderPizza(String desc)
    {
        Pizza pizza = createPizza(desc);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    public abstract Pizza createPizza(String desc);
}
