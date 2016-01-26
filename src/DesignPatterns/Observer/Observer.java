package DesignPatterns.Observer;

/**
 * Created by xuch on 2016/1/25.
 */
public interface Observer
{
    void update(float temperature, float humidity, float pressure);
    void display();
}
