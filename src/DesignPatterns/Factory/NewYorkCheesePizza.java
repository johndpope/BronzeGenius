package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class NewYorkCheesePizza extends Pizza
{
    public NewYorkCheesePizza()
    {
        name = "NY Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }
}
