package GEOLocation.map;

import GEOLocation.geo.GEO;
import GEOLocation.geo.GEOBaidu;
import GEOLocation.config.GEOConfigBaidu;
/**
 * Created by xuch on 2016/1/7.
 */
public class GEOMapSourceBaidu extends GEOMapSource
{
    private static GEOConfigBaidu config = new GEOConfigBaidu();
    private static GEOBaidu geo = new GEOBaidu();

    public String getTableName()
    {
        return config.getSourceId();
    }

    public GEO getGEO()
    {
        return geo;
    }
}
