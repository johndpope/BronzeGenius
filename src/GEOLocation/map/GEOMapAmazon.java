package GEOLocation.map;

import GEOLocation.config.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by xuch on 2016/1/6.
 */
public class GEOMapAmazon extends GEOMap
{
    public String getTableName()
    {
        return GEOConfig.AMAZON_SOURCE_ID;
    }


    /*
    * Get all amazon province+city+district list
    * File erroe, throw an exception
    * */
    public HashMap<String, String> getGEOLocations() throws IOException
    {
        HashMap<String, String> addresses = new HashMap<>();
        HSSFSheet table = getTable(getTableName());
        if ( table == null ) throw new FileNotFoundException(getTableName() + "not found.");//table = createTable(getTableName());
        Iterator<Row> rowIterator = table.iterator();

        // the first row
        if ( rowIterator.hasNext() )
        {
            Row row = rowIterator.next();

            try
            {
                if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS )
                    throw new IOException("Row " + row.getRowNum() + ": File Format is damaged!");
            }
            catch (IOException  e)
            {
                e.printStackTrace();
            }
        }

        while ( rowIterator.hasNext() )
        {
            Row row = rowIterator.next();

            try
            {
                if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS )
                    throw new IOException("Row " + row.getRowNum() + ": File Format is damaged!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                continue;
            }

            String id = "";
            String address = "";
            Iterator<Cell> cellIterator = row.cellIterator();
            if ( cellIterator.hasNext() )
            {
                Cell cell = cellIterator.next();
                id = ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf((int)cell.getNumericCellValue()) : cell.toString().trim() );
            }

            while ( cellIterator.hasNext() )
            {
                Cell cell = cellIterator.next();
                address += cell.toString().trim();
            }

            if ( !id.isEmpty() && !address.isEmpty() ) {
                addresses.put(id, address);
            }
        }

        return addresses;
    }

    /*
    * Get all amazon locations in style <"key", "province,city,district">
    * File erroe, throw an exception
    * */
    public HashMap<String, ArrayList<String>> getAmazonLocations() throws IOException
    {
        HashMap<String, ArrayList<String>> addresses = new HashMap<>();
        HSSFSheet table = getTable(getTableName());
        if ( table == null ) throw new FileNotFoundException(getTableName() + "not found.");//table = createTable(getTableName());
        Iterator<Row> rowIterator = table.iterator();

        // the first row
        if ( rowIterator.hasNext() )
        {
            Row row = rowIterator.next();

            try
            {
                if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS )
                    throw new IOException("Row " + row.getRowNum() + ": File Format is damaged!");
            }
            catch (IOException  e)
            {
                e.printStackTrace();
            }
        }

        while ( rowIterator.hasNext() )
        {
            Row row = rowIterator.next();

            try
            {
                if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS || row.getLastCellNum() != GEOConfig.FORMATTED_TABLE_COLUMNS )
                    throw new IOException("Row " + row.getRowNum() + ": File Format is damaged!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                continue;
            }

            String id = "";
            ArrayList<String> address = new ArrayList<>();
            Iterator<Cell> cellIterator = row.cellIterator();
            if ( cellIterator.hasNext() )
            {
                Cell cell = cellIterator.next();
                id = ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ? String.valueOf((int)cell.getNumericCellValue()) : cell.toString().trim() );
            }

            while ( cellIterator.hasNext() )
            {
                Cell cell = cellIterator.next();
                address.add(cell.toString().trim()); // append at the end of list
            }

            if ( !id.isEmpty() && !address.isEmpty() ) {
                addresses.put(id, address);
            }
        }

        return addresses;
    }

}
