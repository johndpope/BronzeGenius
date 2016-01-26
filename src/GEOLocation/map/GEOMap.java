package GEOLocation.map;

import GEOLocation.config.GEOConfig;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.HashMap;

/**
 * Created by xuch on 2016/1/6.
 */
public abstract class GEOMap implements GEOMapInterface
{
    public String getPath()
    {
        return "/" + GEOConfig.DB_RESOURCE + "/" + GEOConfig.DATABASE;
    }
    /*
    * if not exist throw an exception
    * */
    public InputStream getFile() throws IOException
    {
        String path = getPath();
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) throw new IOException(path + "not exist in jar file.");
        return is;
    }

    /*
    * if not exist throw an exception, only work when not in jar
    * */
    public File getFileO() throws IOException
    {
        String path = getPath();
        java.io.File file = new java.io.File(path);
        System.out.println(file.getAbsolutePath());
        //System.out.println(System.getProperty("user.dir"));
        if ( !file.exists() ) throw new FileNotFoundException("Excel File can not be found!");
        return file;
    }

    public abstract String getTableName();

    /*
    * return null if table not exist, then we can check if we need to create one if not exist
    * if file not exist or cannot open, throw an exception
    * */
    public HSSFSheet getTable(String name) throws IOException
    {
        try
        {
            //FileInputStream in = new FileInputStream(getFile()); // getFile if file not exist, throw an exception
            InputStream in = getFile();
            HSSFWorkbook workbook = new HSSFWorkbook(in);      // throw exception if fileinputstream cannot be read
            in.close();
            return workbook.getSheet(name);            // if not exist, return null
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public HSSFSheet getMyTable() throws IOException
    {
        try
        {
            return getTable(getTableName());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /*
    * return table if successful
    * if file not exist or cannot open, throw an exception
    * */
    public HSSFSheet createTable(String name) throws IOException
    {
        try
        {
            HSSFSheet table = getTable(name);
            if (table == null)
            {
                InputStream in = getFile(); // getFile if file not exist, throw an exception
                HSSFWorkbook workbook = new HSSFWorkbook(in);      // throw exception if fileinputstream cannot be read
                in.close();
                table = workbook.createSheet(name);
                FileOutputStream out = new FileOutputStream(getFileO());
                workbook.write(out);
                out.close();
            }
            return table;
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    public HSSFSheet createMyTable() throws IOException
    {
        try
        {
            return createTable(getTableName());
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    /*
    * Get the key from province + city + district in Amazon/Source table
    *
    * Success: return String
    * Failure: return null
    * */
    public String getKey(String province, String city, String district)
    {
        if (province == null || province.isEmpty() || city == null || city.isEmpty() || district == null || district.isEmpty()) return null;
        try
        {
            HSSFSheet table = getTable(getTableName()); // get Amazon table
            for ( Row row : table )
            {
                if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS ) continue;
                if ( row.getCell(1).getStringCellValue().trim().equals(province) && row.getCell(2).getStringCellValue().trim().equals(city) && row.getCell(3).getStringCellValue().trim().equals(district) ) return ( row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf((int)row.getCell(0).getNumericCellValue()) : row.getCell(0).toString().trim() );
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
    * Get the key from province + city in Amazon/Source table
    *
    * Success: return String
    * Failure: return null
    * */
    public String getKey(String province, String city)
    {
        if ( province != null && !province.isEmpty() && city != null || !city.isEmpty() )
        {
            try
            {
                HSSFSheet table = getTable(getTableName());
                for (Row row : table)
                {
                    if (row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS)
                        continue;
                    if (row.getCell(1).getStringCellValue().trim().equals(province) && row.getCell(2).getStringCellValue().trim().equals(city) )
                        return (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf((int) row.getCell(0).getNumericCellValue()) : row.getCell(0).toString().trim());
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
    * Get the key from province in Amazon/Source table
    *
    * Success: return String
    * Failure: return null
    * */
    public String getKey(String province)
    {
        if ( province != null && !province.isEmpty() )
        {
            try
            {
                HSSFSheet table = getTable(getTableName()); // get Amazon table
                for (Row row : table)
                {
                    if (row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS)
                        continue;
                    if (row.getCell(1).getStringCellValue().trim().equals(province) )
                        return (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf((int) row.getCell(0).getNumericCellValue()) : row.getCell(0).toString().trim());
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
    *
    * Get mapped Amazon province+city+district by Source(Ali/Baidu) key
    * Success: return HashMap {province, city, district} in Amazon
    * Failure: return null
    * */
    public HashMap<String, String> getMappedGEOByKey(String key)
    {
        if ( key != null && !key.isEmpty() )
        {
            try
            {
                HashMap<String, String> mapped = new HashMap<>();
                HSSFSheet table = getTable(GEOConfig.AMAZON_SOURCE_ID); // get Amazon table
                for (Row row : table)
                {
                    if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS ) continue;
                    int i = 0;
                    Cell cell = row.getCell(i++);
                    if ( cell != null && key.equals( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf( (int)cell.getNumericCellValue() ) : cell.toString().trim() ))
                    {
                        mapped.put("province", row.getCell(i++).toString().trim());
                        mapped.put("city", row.getCell(i++).toString().trim());
                        mapped.put("district", row.getCell(i).toString().trim());
                        return mapped;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    /*
    * get default distric by province and city
    *
    * return null if no default district found by province + city or exception occurs
    * return district if found
    *
    * */
    public String getDefaultDistrict(String province, String city)
    {
        if ( province != null && !province.trim().isEmpty() && city != null && !city.trim().isEmpty() )
        {
            try
            {
                HSSFSheet table = getTable(GEOConfig.DEFAULT_AMAZON_DISTRICT); // get source(Ali/Baidu) table
                for (Row row : table)
                {
                    if ( row.getPhysicalNumberOfCells() != GEOConfig.DEFAULT_MAPPED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.DEFAULT_MAPPED_TABLE_COLUMNS ) continue;
                    int i = 0;
                    if ( row.getCell(i) != null && row.getCell(i++).toString().trim().equals(province.trim()) && row.getCell(i) != null && row.getCell(i++).toString().trim().equals(city.trim()) && row.getCell(i) != null && !row.getCell(i).toString().trim().isEmpty() )
                    {
                        return row.getCell(i).toString().trim();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

}
