package DesignPattern.Factory;

public abstract class Calculator {
	protected double A;
	protected double B;
	
//	public Calculator(double a, double b)
//	{
//		A = a;
//		B = b;
//	}
//	
//	public Calculator()
//	{
//	}
	
	public void setNumbers(double a, double b)
	{
		A = a;
		B = b;
	}
	
	public abstract double getResult() throws Exception;
}
