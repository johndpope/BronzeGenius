package DesignPatterns.Observer;

import java.util.*;

/**
 * Created by xuch on 2016/1/26.
 */
public class CurrenConditionsDisplayNew implements java.util.Observer
{
    Observable observable;
    private float temperature;
    private float humidity;

    public CurrenConditionsDisplayNew(Observable observable)
    {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if ( o instanceof WeatherDataNew )
        {
            WeatherDataNew weatherData = (WeatherDataNew)o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();

        }
    }

    public void display()
    {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
