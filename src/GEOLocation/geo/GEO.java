package GEOLocation.geo;

import GEOLocation.config.GEOConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by xuch on 2016/1/7.
 */
public abstract class GEO implements GEOInterface
{
    /*
    * Abstract methods
    * */
    public abstract String getURL(String address) throws IOException;

    public abstract HashMap<String, String> extractResult(String response);

    /*
    * Defined Methods
    * */
    public String getResponse(String url)
    {
        try
        {
            URLConnection connection = urlConnectionInitialization(url);
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String response = readAll(rd).trim();
            rd.close();
            ((HttpURLConnection)connection).disconnect();
            return response;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public URLConnection urlConnectionInitialization(String url) throws IOException
    {
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(GEOConfig.TIMEOUT_VALUE);
        connection.setReadTimeout(GEOConfig.TIMEOUT_VALUE);
        connection.setRequestProperty("Accept-Charset", GEOConfig.CHARSET);
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");

        return connection;
    }

    public String readAll(BufferedReader rd) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ( ( line = rd.readLine() ) != null ) stringBuilder.append(line);
        return stringBuilder.toString();
    }

    /*
    * return {province, city, district, status, info}
    * */
    public HashMap<String, String> geo(String address)
    {
        String response = "";
        try
        {
            String url = getURL(address);
            response = getResponse(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("response from " + this.getClass() + ": " + response);
        return extractResult(response);
    }

    /*
    * return {province, city, district, status, info}
    * */
    public abstract HashMap<String, String> getGEO(String address);
}
