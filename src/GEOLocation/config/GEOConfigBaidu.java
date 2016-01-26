package GEOLocation.config;

/**
 * Created by xuch on 2016/1/7.
 */
public class GEOConfigBaidu implements GEOConfig
{
    //http://api.map.baidu.com/geocoder/v2/?address=%E5%8C%97%E4%BA%AC%E5%B8%82%E6%B5%B7%E6%B7%80%E5%8C%BA%E4%B8%8A%E5%9C%B0%E5%8D%81%E8%A1%9710%E5%8F%B7&output=json&ak=E4805d16520de693a3fe707cdc962045&callback=showLocation
    private static String URL = "http://api.map.baidu.com/geocoder";
    private static String KEY = "92AMQGgcivLefzPAfbx0OYkL"; //"eOCylZUzjNFxkNn8ZFX0ZQxI";
    private static String VERSION = "v2";
    private static String CHARSET = "UTF-8";
    private static String OUTPUT = "json";
    private static String CALLBACK = "showLocation";
    private static String CALLBACK_REVERSE = "renderReverse";
    private static String POIS = "0"; // 1 meas display, 0 not display
    private static String SOURCE_ID = "baidu";

    public String getURL() { return URL; }

    public String getKEY() { return KEY; }

    public String getVERSION() { return VERSION; }

    public String getCHARSET() { return CHARSET; }

    public String getOUTPUT() { return OUTPUT; }

    public String getCALLBACK() { return CALLBACK; }

    public String getCallbackReverse() { return CALLBACK_REVERSE; }

    public String getPOIS() { return POIS; }

    public String getSourceId() { return SOURCE_ID; }
}
