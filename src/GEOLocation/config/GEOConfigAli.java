package GEOLocation.config;

/**
 * Created by xuch on 2016/1/7.
 */
public class GEOConfigAli implements GEOConfig
{
    private static String  URL = "http://restapi.amap.com/v3/geocode/geo";
    private static String KEY = "20abdf3d50f99cdae90e14f1ea8c3d70";
    private static String OUTPUT = "json";
    private static String CHARSET = "UTF-8";
    private static int TIMEOUT_VALUE = 1000 * 1000;
    private static String SOURCE_ID = "ali";


    public String getURL() { return URL; }

    public String getKEY() { return KEY; }

    public String getOUTPUT() {return OUTPUT; }

    public String getCHARSET() {return CHARSET; }

    public int getTimeoutValue() { return TIMEOUT_VALUE; }

    public String getSourceId() { return SOURCE_ID; }
}
