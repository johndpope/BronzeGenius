package DesignPatterns.Observer;

/**
 * Created by xuch on 2016/1/25.
 */
public class WeatherStation
{
    public static void main(String[] args)
    {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);


        weatherData.setMeasurements(80, 65, 40.0f);
        weatherData.setMeasurements(88, 57, 23.9f);
        weatherData.setMeasurements(68, 60, 35.4f);

        WeatherDataNew weatherDataNew = new WeatherDataNew();
        CurrenConditionsDisplayNew currenConditionsDisplayNew = new CurrenConditionsDisplayNew(weatherDataNew);
        weatherDataNew.setMeasurements(88, 57, 23.9f);

    }
}
