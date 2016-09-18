package GeneticAlgorithm;

/**
 * Created by xuch on 9/13/16.
 */
public interface Selection<T, S>
{
    S execute(T t);
}
