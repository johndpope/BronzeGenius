package GEOLocation.geo;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by xuch on 2016/1/7.
 */
public interface GEOInterface {
    String getResponse(String address);
    URLConnection urlConnectionInitialization(String address) throws IOException;
    String readAll(BufferedReader rd) throws IOException;
    String getURL(String address) throws IOException;
    HashMap<String, String> extractResult(String response);
    HashMap<String, String> getGEO(String address);
    HashMap<String, String> geo(String address);
    //HashMap<String, String> geoReverse(String address);
}
