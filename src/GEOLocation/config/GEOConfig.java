package GEOLocation.config;

/**
 * Created by xuch on 2016/1/7.
 */
public interface GEOConfig
{
    int TIMEOUT_VALUE = 1000 * 1000;
    int FORMATTED_TABLE_COLUMNS = 4;
    int DEFAULT_MAPPED_TABLE_COLUMNS = 3;

    String CHARSET = "UTF-8";
    String DATABASE = "db.xls";
    String DB_RESOURCE = "database";
    String AMAZON_SOURCE_ID = "amazon";
    String DEFAULT_AMAZON_DISTRICT = "default_amazon_district";
}
