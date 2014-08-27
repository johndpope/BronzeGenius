package DesignPattern.Strategy;

public class Product {
	protected double price;
	protected String name;
	
	public Product(double p, String n)
	{
		price = p;
		name = n;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
