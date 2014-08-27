package DesignPattern.Factory;

import java.io.IOException;
import java.math.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.console().readLine();
		double a = 12.5, b = 13.5;
		char opr = '/';
		Calculator cal = CalculatorManager.getCalculator(opr);
		cal.setNumbers(a,b);
		System.out.printf("%f %s %f = %f", a, opr, b, cal.getResult());
	}

}
