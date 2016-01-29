package DesignPatterns.Factory;

/**
 * Created by xuch on 2016/1/28.
 */
public class PizzaTestDrive
{
    public static void main(String[] args)
    {
        PizzaStore nyStore = new NewYorkPizzaStore();
        PizzaStore haStore = new HawaiiPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Tony ordered a " + pizza.getName() + "\n");

        pizza = haStore.orderPizza("fruit");
        System.out.println("Lyroe ordered a " + pizza.getName() + "\n");
    }
}
