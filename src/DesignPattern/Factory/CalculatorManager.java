package DesignPattern.Factory;

public class CalculatorManager {
	protected static Calculator cal = null;
	
	
	public static Calculator getCalculator(char operator)
	{
		switch (operator)
		{
		case '+' :
			cal = new CalculatorAdd();
			break;
		case '-' :
			cal = new CalculatorMinus();
			break;
		case '*' :
			cal = new CalculatorMultiple();
			break;
		case '/' :
			cal = new CalculatorDivision();
			break;
		}
		
		return cal;
	}
}
