package GEOLocation.map;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuch on 2016/1/6.
 */
public interface GEOMapInterface
{
    /*enum FORMATTED_TABLE
    {
        AMAZON_TABLE_COLUMNS(4), SOURCE_TABLE_COLUMNS(4), DEFAULT_MAPPED_TABLE_COLUMNS(3);
        FORMATTED_TABLE(int a)
        {

        }
    }*/
    public String getPath();
    public InputStream getFile() throws IOException;
    public File getFileO() throws IOException;
    public String getTableName();
    public HSSFSheet getTable(String name) throws IOException;
    public HSSFSheet createTable(String name) throws IOException;
}
