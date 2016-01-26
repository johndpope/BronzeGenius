package DesignPatterns.Observer;

/**
 * Created by xuch on 2016/1/25.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement
{
    private float temperature;
    private float humidity;
    private Subject weatherData;
    @Override
    public void display()
    {
        System.out.println("Current conditions: " + temperature + "F degrees ad " + humidity + "% humidity");
    }

    public CurrentConditionsDisplay(Subject subject)
    {
        this.weatherData = subject;
        weatherData.registerObserver(this);
    }

    public void update(float temperature, float humidity, float pressure)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
}
