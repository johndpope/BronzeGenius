package GEOLocation.map;

import GEOLocation.config.GEOConfigAli;
import GEOLocation.geo.GEO;
import GEOLocation.geo.GEOAli;

/**
 * Created by xuch on 2016/1/7.
 */
public class GEOMapSourceAli extends GEOMapSource {
    private static GEOConfigAli config = new GEOConfigAli();
    private static GEOAli geo = new GEOAli();

    public String getTableName()
    {
        return config.getSourceId();
    }

    public GEO getGEO()
    {
        return geo;
    }
}
