package DesignPatterns.Observer;

import java.util.Observable;

/**
 * Created by xuch on 2016/1/26.
 */
public class WeatherDataNew extends Observable
{
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDataNew() {}

    public void measurementsChanged()
    {
        setChanged();
        notifyObservers(); // no data passed, we use pull
    }

    public void setMeasurements(float temperature, float humidity, float pressure)
    {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature()
    {
        return temperature;
    }

    public float getHumidity()
    {
        return humidity;
    }

    public float getPressure()
    {
        return pressure;
    }
}
