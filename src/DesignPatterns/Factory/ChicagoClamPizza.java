package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class ChicagoClamPizza extends Pizza
{
    public ChicagoClamPizza()
    {
        name = "Chicago Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    void cut()
    {
        System.out.println("Cutting the pizza into square slices");
    }
}
