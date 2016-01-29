package DesignPatterns.Decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuch on 2016/1/26.
 */
public class StarBuzzCoffee
{
    public static void main(String[] args)
    {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + "$" + beverage.cost());

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1, new Price(0.5f));
        beverage1 = new Milk(beverage1, new Price(0.39f));
        beverage1 = new Whip(beverage1, new Price(0.29f));
        System.out.println(beverage1.getDescription() + "$" + beverage1.cost());

        BeverageWithSize beverageWithSize = new BeverageWithSize(beverage1, new LargeSize());
        System.out.println(beverageWithSize.getDescription() + "$" + beverageWithSize.cost());

        // double mocha, double milk, single whip, middle size cup houseblend
        Beverage beverage2 = new HouseBlend(); // 1.8f
        beverage2 = new Mocha(beverage2, new Price(1.1f));
        beverage2 = new Mocha(beverage2, new Price(1.1f));
        beverage2 = new Milk(beverage2, new Price(0.8f));
        beverage2 = new Milk(beverage2, new Price(0.9f));
        beverage2 = new Whip(beverage2, new Price(0.8f));
        BeverageWithSize beverageWithSize1 = new BeverageWithSize(beverage2, new LargeSize());
        System.out.println(beverageWithSize1.getDescription() + "$" + beverageWithSize1.cost());

        byte a = 'a';
        System.out.println("a: " + a);

        try
        {
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(StarBuzzCoffee.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/resource/note.txt")));
            int c;
            while ( (c = in.read()) != -1) System.out.println((char)c);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
