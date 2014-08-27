package DesignPattern.Strategy;

import java.util.ArrayList;

public class StrategyManager {
	public ArrayList<Strategy> strategies;
	
	public ArrayList<Strategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}

	public StrategyManager()
	{
		//getStrategiesRecordFromDataBase()
		//Initialize strategies;
	}
	
}
