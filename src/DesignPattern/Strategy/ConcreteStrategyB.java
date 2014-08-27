package DesignPattern.Strategy;

public class ConcreteStrategyB extends Strategy {

	@Override
	public double AlgorithmInterface(double total) {
		// TODO Auto-generated method stub
		if (total > 500) return total * 0.8;
		return total;
	}

}
