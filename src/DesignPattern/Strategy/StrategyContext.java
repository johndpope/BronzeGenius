package DesignPattern.Strategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class StrategyContext {
	public static double total = 0;
	protected Map<Product, Integer> products = null;
	protected StrategyManager  strategyManager = null;
	
	public StrategyContext(Map<Product, Integer> prods)
	{
		products = prods;
		strategyManager = new StrategyManager();
	}
	
	/*public StrategyContext(String flag)
	{
		switch(Integer.parseInt(flag))
		{
		case 1:
			strategy = new ConcreteStrategyA();
			break;
		case 2:
			strategy = new ConcreteStrategyB();
			break;
		case 3:
			strategy = new ConcreteStrategyC();
			break;
		default:
			strategy = new ConcreteStrategyA();
			break;
		}
	}*/
	
	public double executeStrategy()
	{
		for (Product p :  products.keySet())
		{
			total += p.getPrice() * products.get(p);
		}
		
		/*for (Entry<Product, Integer> p :  products.entrySet())
		{
			total += p.getKey().getPrice() * p.getValue();
		}*/
		
		for (Strategy strategy : strategyManager.getStrategies())
		{
			total = strategy.AlgorithmInterface(total);
		}
		
		return total;
	}
}
