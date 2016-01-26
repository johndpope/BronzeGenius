package GEOLocation;

import GEOLocation.map.GEOMapSource;
import GEOLocation.map.GEOMapSourceAli;
import GEOLocation.map.GEOMapSourceBaidu;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuch on 2016/1/7.
 */
public class Tool
{
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
            System.out.println("Response from " + geomap.getGEO().getClass() + ":" + response.toString());
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
                System.out.println("response from " + e.getKey().getGEO().getClass() + response.get("province") + "," + response.get("city") + "," + response.get("district"));
                HashMap<String, String> mapAmazon = e.getKey().getMappedGEOProvinceAndCity(response.get("province"), response.get("city"));
                if (mapAmazon == null) System.out.println("Mapped Amazon:" + "NULL");
                if ( mapAmazon != null ) // 不需要检验containsKey("province")&("city")，因为如果不存在key，get会返回null
                {
                    String defaultDistrict = e.getKey().getDefaultDistrict(mapAmazon.get("province"), mapAmazon.get("city"));
                    System.out.println("Mapped Amazon:" + ((defaultDistrict == null || defaultDistrict.isEmpty()) ? "NULL" : defaultDistrict));
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

    public static String getGEOLocationForTest_12_(String address)
    {
        return convertMapToJson(getGEOLocationPrivateForTest_12_(address));
    }
    private static HashMap<String, String> getGEOLocationPrivateForTest_12_(String address)
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

        // get DISTRICT from Source(ali/baidu)
        for ( GEOMapSource geomap : geomaps )
        {
            // getGEO(address) returns: {province, city, district, status, info} 不可能返回为null
            HashMap<String, String> response = geomap.getGEO().getGEO(address);
            System.out.println("Response from " + geomap.getGEO().getClass() + ":" + response.toString());
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
        }
        // do not get default district
        return result;
    }

    public static HashMap<String, HashMap<String, String>> getGEOLocationPrivateForTest_14(String address)
    {
        HashMap<String, HashMap<String, String>> result = new HashMap<>();
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
            System.out.println("Response from " + geomap.getGEO().getClass() + ":" + response.toString());
            if ( response.containsKey("district") )
            {
                // return HashMap {province, city, district} in Amazon, or null
                HashMap<String, String> map = geomap.getMappedGEO( response.get("province"), response.get("city"), response.get("district") );
                if ( map != null )
                {
                    response.put("MappedProvince", map.get("province"));
                    response.put("MappedCity", map.get("city"));
                    response.put("MappedDistrict", map.get("district"));
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
                System.out.println("response from " + e.getKey().getGEO().getClass() + response.get("province") + "," + response.get("city") + "," + response.get("district"));
                HashMap<String, String> mapAmazon = e.getKey().getMappedGEOProvinceAndCity(response.get("province"), response.get("city"));
                if (mapAmazon == null) System.out.println("Mapped Amazon:" + "NULL");
                if ( mapAmazon != null ) // 不需要检验containsKey("province")&("city")，因为如果不存在key，get会返回null
                {
                    String defaultDistrict = e.getKey().getDefaultDistrict(mapAmazon.get("province"), mapAmazon.get("city"));
                    System.out.println("Default Amazon District:" + ((defaultDistrict == null || defaultDistrict.isEmpty()) ? "NULL" : defaultDistrict));
                    response.put("DefaultDistrict", defaultDistrict);
                }
            }
            String name = e.getKey().getGEO().getClass().toString().trim();
            result.put(name.substring(name.lastIndexOf(".") + 1), response);
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
