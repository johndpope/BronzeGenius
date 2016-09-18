package GEOLocation;

import GEOLocation.map.GEOMapSource;
import GEOLocation.map.GEOMapSourceAli;
import GEOLocation.map.GEOMapSourceBaidu;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
*   status = 0， 请求失败：参数不合理（key过期，key不正确，address为空或有非法字符），访问超限等等
*   status = 1，参数完整，查询成功
*       若district字段存在，则查询有结果
*       若district字段不存在，则未查询到结果
*
* */
public class GEOLocation {

    /*
     * status = 1,请求成功，返回json: {status, info, province, city, district}，province、city、district总是同时出现，要么都不出现，同时出现时，字段可能为空串
     * status = 0,请求失败，输入参数有错或者访问受限，返回json: {status, info}
     * status = -1，网络连接错误，返回json: {status, info}
     * */
    public static String getGEOLocation(String address)
    {
        return convertMapToJson(getGEOLocationPrivate(address));
    }

    /*
    * status = 1,请求成功，返回完整的{status, info, province, city, district} province、city、district总是同时出现，要么都不出现，同时出现时，字段可能为空串
    * status = 0,请求失败，输入参数有错或者访问受限，返回{status, info}
    * status = -1，网络连接错误，返回{status, info}
    * */
    private static HashMap<String, String> getGEOLocationPrivate(String address)
    {
        //  初始化，只要后续有一次访问成功，那么就会reset status和info字段
        HashMap<String, String> result = new HashMap<String, String>()
        {
            {
                put("status", "-1");
                put("info", "GEO_API_CONNECTION_ERROR");
            }
        };

        ArrayList<GEOMapSource> geomaps = new ArrayList<GEOMapSource>()
        {
            {
                add(new GEOMapSourceAli());
                add(new GEOMapSourceBaidu());
            }
        };

        HashMap<GEOMapSource, HashMap<String, String>> history = new HashMap<>();

        // get DISTRICT from Source(ali/baidu)
        for ( GEOMapSource geomap : geomaps )
        {
            // getGEO(address) returns: {province, city, district, status, info} 不可能返回为null
            HashMap<String, String> response = geomap.getGEO().getGEO(address);
            result.put("status", response.get("status"));
            result.put("info", response.get("info"));
            if ( response.containsKey("district") )
            {
                // return HashMap {province, city, district} in Amazon, or null
                HashMap<String, String> map = geomap.getMappedGEO( response.get("province"), response.get("city"), response.get("district") );
                if ( map != null )
                {
                    result.put("province", map.get("province"));
                    result.put("city", map.get("city"));
                    result.put("district", map.get("district"));
                    return result;
                }
            }
            history.put(geomap, response);
        }

        // if get DISTRICT from Source(ali/baidu) failed
        // then get DEFAULT_DISTRICT from Amazon table
        for ( Map.Entry<GEOMapSource, HashMap<String, String>> e : history.entrySet() )
        {
            HashMap<String, String> response = e.getValue();
            if ( response != null && response.containsKey("province") && response.containsKey("city") )
            {
                HashMap<String, String> mapAmazon = e.getKey().getMappedGEOProvinceAndCity(response.get("province"), response.get("city"));
                if ( mapAmazon != null ) // 不需要检验containsKey("province")&("city")，因为如果不存在key，get会返回null
                {
                    String defaultDistrict = e.getKey().getDefaultDistrict(mapAmazon.get("province"), mapAmazon.get("city"));
                    if ( defaultDistrict != null && !defaultDistrict.isEmpty() )
                    {
                        result.put("status", "1");
                        result.put("info", "DEFAULT_AMAZON_DISTRICT");
                        result.put("province", mapAmazon.get("province"));
                        result.put("city", mapAmazon.get("city"));
                        result.put("district", defaultDistrict);
                        return result;
                    }
                }
            }
        }

        return result;
    }

    /*
    *  要求传送与Amazon一致的province、city信息
    *
    * status = 1,请求成功，返回完整的{status, info, province, city, district}, province、city、district总是同时出现，要么都不出现，同时出现时，字段可能为空串
    * status = 0,请求失败，输入参数有错或者访问受限，返回{status, info}
    * status = -1，网络连接错误，返回{status, info}
    * */
    private static HashMap<String, String> getGEOLocationPrivate2_0(String province, String city, String address)
    {
        HashMap<String, String> result = new HashMap<String, String>()
        {
            {
                put("status", "-1");
                put("info", "GEO_API_CONNECTION_ERROR");
            }
        };

        ArrayList<GEOMapSource> geomaps = new ArrayList<GEOMapSource>()
        {
            {
                add(new GEOMapSourceAli());
                add(new GEOMapSourceBaidu());
            }
        };

        HashMap<GEOMapSource, HashMap<String, String>> history = new HashMap<>();

        // get DISTRICT from Source(ali/baidu)
        for ( GEOMapSource geomap : geomaps )
        {
            // getGEO(address) returns: {province, city, district, status, info} 不可能返回为null
            HashMap<String, String> response = geomap.getGEO().getGEO(address);
            result.put("status", response.get("status"));
            result.put("info", response.get("info"));
            if ( response.containsKey("district") ) {
                // return HashMap {province, city, district} in Amazon, or null
                HashMap<String, String> map = geomap.getMappedGEO( response.get("province"), response.get("city"), response.get("district") );
                if ( map != null )
                {
                    result.put("province", map.get("province"));
                    result.put("city", map.get("city"));
                    result.put("district", map.get("district"));
                    return result;
                }
            }
            history.put(geomap, response);
        }

        // if get DISTRICT from Source(ali/baidu) failed
        // then get DEFAULT_DISTRICT from Amazon table
        for ( Map.Entry<GEOMapSource, HashMap<String, String>> e : history.entrySet() )
        {
            HashMap<String, String> response = e.getValue();
            if ( response != null && response.containsKey("province") && response.containsKey("city") )
            {
                HashMap<String, String> mapAmazon = e.getKey().getMappedGEOProvinceAndCity(response.get("province"), response.get("city"));
                if ( mapAmazon != null ) // 不需要检验containsKey("province")&("city")，因为如果不存在key，get会返回null
                {
                    String defaultDistrict = e.getKey().getDefaultDistrict(mapAmazon.get("province"), mapAmazon.get("city"));
                    if ( defaultDistrict != null && !defaultDistrict.isEmpty() )
                    {
                        result.put("status", "1");
                        result.put("info", "DEFAULT_AMAZON_DISTRICT");
                        result.put("province", mapAmazon.get("province"));
                        result.put("city", mapAmazon.get("city"));
                        result.put("district", defaultDistrict);
                        return result;
                    }
                }
            }
        }

        String defaultDistrict = geomaps.get(0).getDefaultDistrict(province, city);
        if ( defaultDistrict != null && !defaultDistrict.isEmpty() )
        {
            result.put("status", "1");
            result.put("info", "DEFAULT_AMAZON_DISTRICT");
            result.put("province", province);
            result.put("city", city);
            result.put("district", defaultDistrict);
            return result;
        }

        return result;
    }

    /*
    *  要求传送与Amazon一致的province、city信息，而且还有district信息（是否必须符合Amazon district列表，暂时未定）
    *
    * status = 1,请求成功，返回完整的{status, info, province, city, district}, province、city、district总是同时出现，要么都不出现，同时出现时，字段可能为空串
    * status = 0,请求失败，输入参数有错或者访问受限，返回{status, info}
    * status = -1，网络连接错误，返回{status, info}
    * */
    private static HashMap<String, String> getGEOLocationPrivate3_0(String province, String city, String district, String address)
    {
        return null;
    }

    /*
    * return "{}" if map is empty
    * */
    public static String convertMapToJson(HashMap<String, String> mp) {
        JSONObject jo = new JSONObject(mp);
        return jo.toString();
    }

}
