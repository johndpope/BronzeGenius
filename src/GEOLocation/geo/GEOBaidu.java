package GEOLocation.geo;

import GEOLocation.config.GEOConfigBaidu;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by xuch on 2016/1/7.
 */
public class GEOBaidu extends GEO
{
    private static GEOConfigBaidu config = new GEOConfigBaidu();

    // baidu api should not add callback parameter, because a returned result with callback parameter is not valid json string
    public String getURL(String address) throws IOException
    {
        String query = String.format(
                "ak=%s&address=%s&output=%s",
                URLEncoder.encode(config.getKEY(), config.getCHARSET()),
                URLEncoder.encode(address, config.getCHARSET()),
                URLEncoder.encode(config.getOUTPUT(), config.getCHARSET())
        );
        return config.getURL() + "/" + config.getVERSION() + "/?" + query;
    }

    public String getURLReverse(String lat_lon) throws IOException
    {
        String query = String.format(
                "ak=%s&location=%s&output=%s",
                URLEncoder.encode(config.getKEY(), config.getCHARSET()),
                URLEncoder.encode(lat_lon, config.getCHARSET()),
                URLEncoder.encode(config.getOUTPUT(), config.getCHARSET())
        );
        return config.getURL() + "/" + config.getVERSION() + "/?" + query;
    }

    /*
    * 请求成功的情况下，即response != ""时
    *   只有当baidu返回status = 0时，才说明请求成功
    *   否则任何status值，都直接返回请求失败，即参数不对，或者IP访问受限制等
    *       baidu请求失败的话，会返回msg或者message字段，msg优先。日他大爷，这API真不规范。
    *
    *   Anyway, return hashmap: { status, info[, longitude, latitude] }
    * */
    public HashMap<String, String> extractResult(String response)
    {
        HashMap<String, String> mp = new HashMap<>();
        if ( !response.equals("") )
        {
            mp.put("status", "0");
            mp.put("info", "GEO_API_RETURN_NULL");
            try
            {
                JSONObject jsonObject = new JSONObject(response);

                if ( jsonObject.has("status") && jsonObject.get("status").toString().trim().equals("0") ) { // && jsonObject.has("result")) {
                    mp.put("status", "1");
                    mp.put("info", "OK");
                    try
                    {
                        JSONObject result = new JSONObject(jsonObject.get("result").toString());

                        if ( result.has("location") )
                        {
                            JSONObject location = new JSONObject(result.get("location").toString());

                            if ( location.has("lng") && location.has("lat") )
                            {
                                String lng = location.get("lng").toString().trim();
                                String lat = location.get("lat").toString().trim();

                                if ( !lng.isEmpty() && !lng.equals("[]") && !lat.isEmpty() && !lat.equals("[]") )
                                {
                                    mp.put("longitude", lng);
                                    mp.put("latitude", lat);
                                }
                            }
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    String info = jsonObject.has("msg") ? jsonObject.get("msg").toString().trim() : (jsonObject.has("message") ? jsonObject.get("message").toString().trim() : "GEO_API_RETURN_NULL");
                    mp.put("info", (info.isEmpty() ? "GEO_API_RETURN_NULL" : info));
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return mp;
        }

        mp.put("status", "-1");
        mp.put("info", "GEO_API_CONNECTION_ERROR");
        return mp;
    }

    /*
    * Anyway, return hashmap: { status, info[, province, city, district] }
    *
    * */
    public HashMap<String, String> extractResultReverse(String response)
    {
        HashMap<String, String> mp = new HashMap<>();
        if ( !response.equals("") )
        {
            mp.put("status", "0");
            mp.put("info", "GEO_API_RETURN_NULL");
            try
            {
                JSONObject jsonObject = new JSONObject(response);

                if ( jsonObject.has("status") && jsonObject.get("status").toString().trim().equals("0") ) { // && jsonObject.has("result")) {
                    mp.put("status", "1");
                    mp.put("info", "OK");
                    try
                    {
                        JSONObject result = new JSONObject(jsonObject.get("result").toString().trim());

                        if ( result.has("addressComponent") )
                        {
                            JSONObject addressComponent = new JSONObject(result.get("addressComponent").toString().trim());

                            if ( addressComponent.has("province") )
                            {
                                String province = addressComponent.get("province").toString().trim();
                                if ( !province.isEmpty() && !province.equals("[]") ) mp.put("province", province);
                            }

                            if ( addressComponent.has("city") )
                            {
                                String city = addressComponent.get("city").toString().trim();
                                if ( mp.containsKey("province") && !city.isEmpty() && !city.equals("[]") ) mp.put("city", city);
                            }

                            if ( addressComponent.has("district") )
                            {
                                String district = addressComponent.get("district").toString().trim();
                                if (  mp.containsKey("province") && !district.isEmpty() && !district.equals("[]") )
                                {
                                    mp.put("district", district);
                                    if ( !mp.containsKey("city") ) mp.put("city", "[" + district + "]");
                                }
                            }
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    String info = jsonObject.has("msg") ? jsonObject.get("msg").toString().trim() : (jsonObject.has("message") ? jsonObject.get("message").toString().trim() : "GEO_API_RETURN_NULL");
                    mp.put("info", (info.isEmpty() ? "GEO_API_RETURN_NULL" : info));
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return mp;
        }

        mp.put("status", "-1");
        mp.put("info", "GEO_API_CONNECTION_ERROR");
        return mp;
    }

    /*
    * Anyway, return hashmap: { status, info[, province, city, district] }
    *
    * */
    public HashMap<String, String> geoReverse(String location)
    {
        String response = "";
        try
        {
            String url = getURLReverse(location);
            response = getResponse(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return extractResultReverse(response);
    }

    /*
    * Anyway, return hashmap: { status, info[, province, city, district] }
    *
    * */
    public HashMap<String, String> getGEO(String address)
    {
        HashMap<String, String> response = geo(address);
        if ( response != null && response.containsKey("longitude") && response.containsKey("latitude") )
        {
            response = geoReverse(response.get("latitude") + "," + response.get("longitude"));
        }

        return response;
    }

}
