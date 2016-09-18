package GeneticAlgorithm;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by xuch on 9/13/16.
 */
public class RouletteWheelSelection implements Selection<LinkedList<Double>, Integer>
{
    @Override
    public Integer execute(LinkedList<Double> probabilities)
    {
        double factor = Math.random(); // generate a random number within [0, 1)
        double cumulativeProbability = 0f;
        for (int i = 0; i < probabilities.size(); i++)
        {
            cumulativeProbability += probabilities.get(i);
            if (factor <= cumulativeProbability) return i;
        }
        return probabilities.size();
    }

    // [min, max)
    public static int random(int min, int max)
    {
        return (int)(Math.random() * (max - min + 1) + min);
    }
}
