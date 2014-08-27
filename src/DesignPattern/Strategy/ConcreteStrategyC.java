package DesignPattern.Strategy;

public class ConcreteStrategyC extends Strategy {

	@Override
	public double AlgorithmInterface(double total) {
		// TODO Auto-generated method stub
		if (total > 1000) return total-200;
		return total;
	}

}
