package DesignPatterns.Factory;

import java.util.ArrayList;

/**
 * Created by xuch on 2016/1/28.
 */
public abstract class Pizza
{
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();
    //public abstract void
    void prepare()
    {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding ...");
        System.out.println("Adding toppings:");
        for ( int i = 0; i < toppings.size(); i++ ) System.out.println("  " + toppings.get(i));
    }

    void bake()
    {
        System.out.println("Baking for 25 minutes at 350");
    }

    void cut()
    {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box()
    {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName()
    {
        return name;
    }
}
