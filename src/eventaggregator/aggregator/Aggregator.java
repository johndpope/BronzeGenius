package eventaggregator.aggregator;

/**
 * Created by xuch on 8/30/16.
 */
public interface Aggregator<T, S>
{
    S execute(T t);
}
