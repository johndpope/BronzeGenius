package UnitTest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuch on 2016/1/21.
 */
public class Config
{
    public static String CHARSET = "UTF-8";
    public static String TABLE = "3th_test_result";
    public static String DATABASE = "test_address_list.xls";
    public static String DB_RESOURCE = "database";

    public static void setTestTable(String name)
    {
        TABLE = name;
    }

    public static String getRelativePath()
    {
        return "/" + DB_RESOURCE + "/" + DATABASE;
    }

    public static String getAbsolutePath()
    {
        return Config.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + DB_RESOURCE + "/" + DATABASE;
    }

    public static HSSFWorkbook getDB()
    {
        HSSFWorkbook workbook = null;
        try
        {
            InputStream is = Config.class.getResourceAsStream(getRelativePath());
            if ( is != null ) workbook = new HSSFWorkbook(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return workbook;
    }

    public static HSSFSheet getTable()
    {
        try
        {
            InputStream is = Config.class.getResourceAsStream(getRelativePath());
            if ( is == null ) return null;
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            if (workbook != null) return workbook.getSheet(TABLE); // null if not get
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
