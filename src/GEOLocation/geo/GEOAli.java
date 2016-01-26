package GEOLocation.geo;

import GEOLocation.config.GEOConfigAli;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by xuch on 2016/1/7.
 */
public class GEOAli extends GEO
{
    private static GEOConfigAli config = new GEOConfigAli();

    public String getURL(String address) throws IOException
    {
        String query = String.format(
                "key=%s&address=%s&output=%s",
                URLEncoder.encode(config.getKEY(), config.getCHARSET()),
                URLEncoder.encode(address, config.getCHARSET()),
                URLEncoder.encode(config.getOUTPUT(), config.getCHARSET())
        );
        return config.getURL() + "?" + query;
    }

    public HashMap<String, String> extractResult(String response)
    {
        HashMap<String, String> mp = new HashMap<>();
        if ( !response.equals("") )
        {
            try
            {
                JSONObject jsonObject = new JSONObject(response);
                if ( jsonObject.has("status") && jsonObject.has("info") )
                {
                    String status = jsonObject.get("status").toString().trim();
                    mp.put("status", status);
                    mp.put("info", jsonObject.get("info").toString().trim());
                    if ( status.equals("1") && jsonObject.has("count") && Integer.parseInt(jsonObject.get("count").toString().trim()) > 0 )
                    {
                        String key = "geocodes";
                        if ( jsonObject.has(key) )
                        {
                            try
                            {
                                JSONObject geo = new JSONObject(((JSONArray) jsonObject.get(key)).get(0).toString());

                                if ( geo.has("province") )
                                {
                                    String province = geo.get("province").toString().trim();
                                    if ( !province.isEmpty() && !province.equals("[]") ) mp.put("province", province);
                                }

                                if ( geo.has("city") )
                                {
                                    String city = geo.get("city").toString().trim();
                                    if ( mp.containsKey("province") && !city.isEmpty() && !city.equals("[]") ) mp.put("city", city);
                                }

                                if ( geo.has("district") )
                                {
                                    String district = geo.get("district").toString().trim();
                                    if ( mp.containsKey("province") && !district.isEmpty() && !district.equals("[]") )
                                    {
                                        mp.put("district", district);
                                        if ( !mp.containsKey("city") ) mp.put("city", "[" + district + "]");
                                    }
                                }
                            }
                            catch (Exception e) {
                                return mp;
                            }
                        }
                    }
                    return mp;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        mp.put("status", "-1");
        mp.put("info", "GEO_API_CONNECTION_ERROR");
        return mp;
    }

    public HashMap<String, String> getGEO(String address)
    {
        return geo(address);
    }
}
