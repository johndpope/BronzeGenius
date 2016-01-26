package GEOLocation.map;

import GEOLocation.geo.GEO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuch on 2016/1/6.
 */
public abstract class GEOMapSource extends GEOMap
{
    public abstract String getTableName();
    public abstract GEO getGEO();

    /*
    * Update province + city + district list from Source(Ali/Baidu)
    * if update failed, throw an exception
    * */
    //public abstract void updateSourceGEO() throws Exception;
    private void updateSourceGEO() throws IOException
    {
        HashMap<String, String> response;
        GEOMapAmazon amazon = new GEOMapAmazon();
        HashMap<String, String> original = amazon.getGEOLocations();
        HashMap<String, String> source = new HashMap<>();
        for ( Map.Entry<String, String> e : original.entrySet())
        {
            response = getGEO().getGEO(e.getValue());
            if ( response.containsKey("district") && response.containsKey("city") && response.containsKey("province") ) source.put(e.getKey(), response.get("province") + "," + response.get("city") + "," + response.get("district"));
        }

        updateTable(source);
    }
    /*
    * 1. create a temp sheet and get all geo locations
    * 2. if 1 successfully, replace the old sheet with the new created aheet
    * */
    private void updateTable(HashMap<String, String> geo) throws IOException
    {
        try
        {
            InputStream in = getFile(); // getFile if file not exist, throw an exception
            HSSFWorkbook workbook = new HSSFWorkbook(in);      // throw exception if fileoutputstream cannot be read
            in.close();

            String tempTableName = getTableName() + "-temp";
            if ( workbook.getSheetIndex(tempTableName) != -1 ) workbook.removeSheetAt(workbook.getSheetIndex(tempTableName));
            HSSFSheet table = workbook.createSheet(tempTableName);

            int index = 0;
            for ( Map.Entry<String, String> e : geo.entrySet() )
            {
                String[] locations = (e.getKey() + "," + e.getValue()).split(",");
                Row row = table.createRow(index++);
                for ( int i = 0; i < locations.length; i++ )
                {
                    Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                    cell.setCellValue(locations[i]);
                }
            }

            if ( workbook.getSheetIndex(getTableName()) != -1 ) workbook.removeSheetAt(workbook.getSheetIndex(getTableName()));
            workbook.setSheetName(workbook.getSheetIndex(tempTableName), getTableName());

            FileOutputStream out = new FileOutputStream(getFileO());
            workbook.write(out);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /*
    * Get mapped Amazon province+city+district by Source(Ali/Baidu) province+city+district
    * Success: return HashMap {province, city, district} in Amazon
    * Failure: return null
    * */
    public HashMap<String, String> getMappedGEO(String province, String city, String district)
    {
        String key = getKey(province, city, district);System.out.println("key in amazon:" + key);
        if ( key != null && !key.isEmpty() ) return getMappedGEOByKey(key);
        return null;
    }

    /*
    * Get mapped Amazon "province" + "city" by Source(Ali/Baidu) "province" + "city"
    * Success: return HashMap {province, city} in Amazon
    * Failure: return null
    * */
    public HashMap<String, String> getMappedGEOProvinceAndCity(String province, String city)
    {
        String key = getKey(province, city);
        if ( key != null && !key.isEmpty() )
        {
            HashMap<String, String> response = getMappedGEOByKey(key);
            if ( response != null && response.containsKey("province") && response.containsKey("city") )
                return new HashMap<String, String>()
                {
                    {
                        put("province", response.get("province"));
                        put("city", response.get("city"));
                    }
                };
        }
        return null;
    }

    /*
    * Get mapped Amazon "province" by Source(Ali/Baidu) "province"
    * Success: return HashMap {province} in Amazon
    * Failure: return null
    * */
    public HashMap<String, String> getMappedGEOProvince(String province)
    {
        String key = getKey(province);
        if ( key != null && !key.isEmpty() )
        {
            HashMap<String, String> response = getMappedGEOByKey(key);
            if ( response != null && response.containsKey("province") )
                return new HashMap<String, String>()
                {
                    {
                        put("province", response.get("province"));
                    }
                };
        }
        return null;
    }

}
