package GEOLocation;

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
*   status = -1，API连接错误(API CONNECTION ERROR)：网络错误，GEO API访问限制（例如超过当日访问次数上限，禁止访问），API服务关闭等等
*   status = 0，参数不合理：key过期，key不正确，address为空等等
*   status = 1，参数完整，查询成功
*       若district字段存在，则查询有结果
*       若district字段不存在，则未查询到结果
*
* */
public class GEOLocation {

    public static HashMap<String, String> getGEOLocation(String address) {
        String response = getResponse(address);
        return extractResult(response);
    }

    private static String getResponse(String address) {
        try {
            URLConnection connection = urlConnectionInitialization(address);
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String response = readAll(rd).trim();
            rd.close();
            ((HttpURLConnection)connection).disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static URLConnection urlConnectionInitialization(String address) throws IOException{
        String url = getURL(address);
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept-Charset", GEOConfig.CHARSET);
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"); // Do as if you're using Chrome 41 on Windows 7

        return connection;
    }

    private static String getURL(String address) throws IOException{
        String query = String.format(
                "key=%s&address=%s&output=%s",
                URLEncoder.encode(GEOConfig.KEY, GEOConfig.CHARSET),
                URLEncoder.encode(address, GEOConfig.CHARSET),
                URLEncoder.encode(GEOConfig.OUTPUT, GEOConfig.CHARSET)
        );
        return GEOConfig.URL + "?" + query;
    }

    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            stringBuilder.append(line);//stringBuilder.append('\r');
        }
        return stringBuilder.toString();
    }

    private static HashMap<String, String> extractResult(String response) {
        HashMap<String, String> mp = new HashMap<>();
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.has("status") && jsonObject.has("info")) {
            String status = jsonObject.get("status").toString().trim();
            mp.put("status", status);
            mp.put("info", jsonObject.get("info").toString().trim());
            if (status.equals("1") && jsonObject.has("count") && Integer.parseInt(jsonObject.get("count").toString().trim()) > 0) {
                String key = "geocodes";
                if (jsonObject.has(key)) {
                    JSONObject geo = new JSONObject(((JSONArray) jsonObject.get(key)).get(0).toString());
                    if (geo.has("district") && !geo.get("district").toString().trim().isEmpty() && geo.has("city") && !geo.get("city").toString().trim().isEmpty() && geo.has("province") && !geo.get("province").toString().trim().isEmpty()) {
                        /*
                        * get more info:
                        * {province, city, district}
                        *
                        *then call the function GEOMap.getAmazonMappedLocation(province, city, district)
                        * */
                        String district = geo.get("district").toString().trim();
                        String city = geo.get("city").toString().trim();
                        String province = geo.get("province").toString().trim();
                        mp.put("district", GEOMap.getAmazonMappedDistrict(province, city, district));
                    }
                }
            }
            //else if (status.equals("1")) mp.put
        } else {
            mp.put("status", "-1");
            mp.put("info", "GEO_API_CONNECTION_ERROR");
        }
        return mp;
    }

}
