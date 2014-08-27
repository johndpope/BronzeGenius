package DesignPattern.Factory;

public class CalculatorDivision extends Calculator {

//	public CalculatorDivision(double a, double b) {
//		super(a, b);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public double getResult() throws Exception {
		// TODO Auto-generated method stub
		if ( B == 0) throw new Exception();
		return A / B;
	}

}
