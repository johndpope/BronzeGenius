package GEOLocation;

import GEOLocation.geo.*;
import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import org.json.*;


/**
 * Created by xuch on 2015/12/28.
 */

/*
* Input：
*   address：至少有省、市、街道地址或建筑物地址，使用“UTF-8”编码格式
*   -------------------以下参数为配置参数
*       key: API访问key，配置在GEOConfig文件中
*       其他参数同理
*
* Output：
*   status = -1，API_CONNECTION_ERROR：网络错误，访问超时等。此种情况，建议重试。
*   status = 0， 请求失败：参数不合理（key过期，key不正确，address为空），访问超限等等
*   status = 1，参数完整，查询成功
*       若district字段存在，则查询有结果
*       若district字段不存在，则未查询到结果
*
* */
public class GEOLocation {

    public static HashMap<String, String> getGEOLocation(String address) {
        GEOAli ali = new GEOAli();
        HashMap<String, String> result = ali.getGEO(address);
        if (!result.containsKey("district")) {
            GEOBaidu baidu = new GEOBaidu();
            result = baidu.getGEO(address);
        }
        return result;
    }

    public static HashMap<String, String> ali(String address) {
        GEOAli ali = new GEOAli();
        return ali.getGEO(address);
    }

    public static HashMap<String, String> baidu(String address) {
        GEOBaidu baidu = new GEOBaidu();
        return baidu.geo(address);
    }

    public static HashMap<String, String> baidu_(String location) {
        GEOBaidu baidu = new GEOBaidu();
        return baidu.geoReverse(location);
    }
}
