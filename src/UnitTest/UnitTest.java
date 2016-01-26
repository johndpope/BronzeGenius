package UnitTest;

import GEOLocation.GEOLocation;
import GEOLocation.Tool;
import GEOLocation.config.GEOConfig;
import GEOLocation.config.GEOConfigAli;
import GEOLocation.config.GEOConfigBaidu;
import GEOLocation.map.GEOMapAmazon;
import GEOLocation.map.GEOMapSourceAli;
import GEOLocation.map.GEOMapSourceBaidu;
import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

//import com.sun.corba.se.impl.orb.ParserTable;
//import com.sun.glass.ui.SystemClipboard;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by xuch on 2015/12/29.
 */
public class UnitTest {
    public static void main(String[] args) {
        Test();
        //System.exit(0);
    }



    public static void Test() {
        //test_1();
        //test_2();
        //test_3();
        //test_4();
        //test_5();
        //test_6();
        //test_7();
        //test_8();
        //test_9();
        //test_10();
        //test_11();
        //test_12();
        test_12_();
        //test_13();
        //test_14();
    }


    private static void test_1() {

        ArrayList<String> addresses = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座", "远洋国际中心A座", "中国银行大厦", "", "fuck you", "---23dsf@@E$", "安徽合肥市蜀山区经开区书香路71号报业园24号楼712", "贵州贵阳市南明区东山中天世纪新城半山居A1栋3单元201室")); //{"北京市朝阳区远洋国际中心A座", };
        for (String address : addresses) {
            System.out.println("Address: " + address);
            HashMap<String, String> response = GEOLocation.getGEOLocation(address);
            Collection entries = response.entrySet();
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
            System.out.print("\n");
        }
        return;
    }

    private static void test_2() {
        //GEOMap.getFile();
    }

    private static void test_3() {
        //GEOMap.readFile();
    }

    private static void test_4() {
        ArrayList<String> addresses = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座"));
        ArrayList<String> addresses_ = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座", "远洋国际中心A座", "中国银行大厦", "", "fuck you", "---23dsf@@E$", "安徽合肥市蜀山区经开区书香路71号报业园24号楼712", "贵州贵阳市南明区东山中天世纪新城半山居A1栋3单元201室")); //{"北京市朝阳区远洋国际中心A座", };
        for (String address : addresses_) {
            System.out.println("Address: " + address);
            HashMap<String, String> response = GEOLocation.ali(address);
            Collection entries = response.entrySet();
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
            System.out.print("\n");
        }
        return;
    }

    private static void test_5() {
        ArrayList<String> addresses = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座"));
        ArrayList<String> addresses_ = new ArrayList<>(Arrays.asList("北京市朝阳区远洋国际中心A座", "远洋国际中心A座", "中国银行大厦", "", "fuck you", "---23dsf@@E$", "安徽合肥市蜀山区经开区书香路71号报业园24号楼712", "贵州贵阳市南明区东山中天世纪新城半山居A1栋3单元201室")); //{"北京市朝阳区远洋国际中心A座", };
        for (String address : addresses_) {
            System.out.println("Address: " + address);
            HashMap<String, String> response = GEOLocation.baidu(address);
            Collection entries = response.entrySet();
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                System.out.println(e.getKey() + "=" + e.getValue());
            }
            System.out.print("\n");

            if ( response.containsKey("longitude") && response.containsKey("latitude") ) {
                System.out.println("georeverse: ");
                response = GEOLocation.baidu_(response.get("latitude") + "," + response.get("longitude"));
                System.out.println("district: " + response.get("district"));
                entries = response.entrySet();
                it = entries.iterator();
                while ( it.hasNext() ) {
                    Map.Entry e = (Map.Entry) it.next();
                    System.out.println(e.getKey() + "=" + e.getValue());
                }
                System.out.print("\n");
            }
        }
        return;
    }


    private static void test_6()
    {
        GEOMapSourceAli ali = new GEOMapSourceAli();
        GEOMapSourceBaidu baidu = new GEOMapSourceBaidu();
        GEOMapAmazon amazon = new GEOMapAmazon();
        GEOConfigAli configAli = new GEOConfigAli();
        GEOConfigBaidu configBaidu = new GEOConfigBaidu();


        System.out.println(GEOConfig.CHARSET + "\n" + configAli.getCHARSET() + "\n" + configBaidu.getCHARSET());
        System.out.println(ali.getGEO().getClass() + "\n" + baidu.getGEO().getClass());
        System.out.println(amazon.getTableName() + "\n" + ali.getTableName() + "\n" + baidu.getTableName());
        try
        {
            //HSSFSheet table = amazon.getMyTable();
            //HSSFSheet table = ali.getMyTable();
            HSSFSheet table = baidu.getMyTable();
            if (table != null) {
                for ( Row row : table )
                {
                    for ( Cell cell : row )
                    {
                        String value = "";
                        System.out.print(cell.toString() + ",\t" + cell.toString().length() + ",\t");
                        if ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) value = String.valueOf((int)cell.getNumericCellValue());
                        if ( cell.getCellType() == Cell.CELL_TYPE_STRING ) value = cell.toString();//cell.getStringCellValue();
                        System.out.println(cell.getCellType() + ",\t" + value + ",\t" + value.length());
                    }
                    System.out.println();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void test_7()
    {
        GEOMapSourceAli ali = new GEOMapSourceAli();
        GEOMapSourceBaidu baidu = new GEOMapSourceBaidu();
        GEOMapAmazon amazon = new GEOMapAmazon();
        GEOConfigAli configAli = new GEOConfigAli();
        GEOConfigBaidu configBaidu = new GEOConfigBaidu();

        /*try
        {
            ali.updateSourceGEO();
            baidu.updateSourceGEO();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }

    // check exceptional returned result 测试那些ali&baidu返回为空或异常的amazon省市区是否真的返回为空或异常
    private static void test_8()
    {
        // 382 2178 2885 2975 2976
        // 36 42 45 48 110 275 724 754 764 806 1150 1347 1356 1376 1392 1393 1407 1411 1582 1597 1839 1894 1900 1905~1961
        // 2027 2077 2118 2141 2142 2573 2634 2733 2888 2944 2976
        int[] arrAli = { 36, 42, 45, 48, 110, 275, 724, 754, 764, 806, 1150, 1347, 1356, 1376, 1392, 1393, 1407, 1411, 1582, 1597, 1839, 1894, 1900, 2027, 2077, 2118, 2141, 2142, 2573, 2634, 2733, 2888, 2944, 2976 }; // 1905~1961
        int[] arrBaidu = {382, 2178, 2885, 2975, 2976};



        GEOMapSourceAli ali = new GEOMapSourceAli();
        GEOMapSourceBaidu baidu = new GEOMapSourceBaidu();
        GEOMapAmazon amazon = new GEOMapAmazon();
        GEOConfigAli configAli = new GEOConfigAli();
        GEOConfigBaidu configBaidu = new GEOConfigBaidu();
        HashMap<String, String> aliAddresses = new HashMap<>();
        HashMap<String, String> baiduAddresses = new HashMap<>();
        try
        {
            HSSFSheet table = amazon.getMyTable();
            int flag = 0;
            for ( int i = 0, j = 0; i < arrBaidu.length || j < arrAli.length; ) {
                for ( Row row : table )
                {
                    if ( flag++ == 0 ) continue;
                    if ( row.getPhysicalNumberOfCells() != GEOConfig.FORMATTED_TABLE_COLUMNS ) continue;
                    String key = row.getCell(0).getStringCellValue();
                    if ( i < arrBaidu.length && arrBaidu[i] == Integer.parseInt(key) )
                    {
                        baiduAddresses.put(key, row.getCell(1).getStringCellValue().trim() + row.getCell(2).getStringCellValue().trim() + row.getCell(3).getStringCellValue().trim() );
                        System.out.println(key + "=" + baiduAddresses.get(key));
                        i++;
                    }
                    if ( j < arrAli.length && arrAli[j] == Integer.parseInt(key) )
                    {
                        aliAddresses.put(key, row.getCell(1).getStringCellValue().trim() + row.getCell(2).getStringCellValue().trim() + row.getCell(3).getStringCellValue().trim() );
                        System.out.println(key + "=" + aliAddresses.get(key));
                        j++;
                    }
                    if (  Integer.parseInt(key) >= 1905 && Integer.parseInt(key) <= 1961 )
                    {
                        aliAddresses.put(key, row.getCell(1).getStringCellValue().trim() + row.getCell(2).getStringCellValue().trim() + row.getCell(3).getStringCellValue().trim() );
                        System.out.println(key + "=" + aliAddresses.get(key));
                    }
                }
            }

            System.out.println("------------Ali------------");
            for ( Map.Entry<String, String> e : aliAddresses.entrySet() )
            {
                System.out.println( "---" + e.getKey() + ", " + e.getValue() + ", " + Tool.convertMapToJson(ali.getGEO().getGEO(e.getValue())) + "---");
            }

            System.out.println("\n------------Baidu------------");
            for ( Map.Entry<String, String> e : baiduAddresses.entrySet() )
            {
                System.out.println( "---" + e.getKey() + ": " + e.getValue() + ", " + Tool.convertMapToJson(baidu.getGEO().getGEO(e.getValue())) + "---");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    // get the default location
    private static void test_9()
    {
        GEOMapAmazon geomap = new GEOMapAmazon();
        Vector<javafx.util.Pair<String, String>> vec = new Vector<javafx.util.Pair<String, String>>()
        {
            {
                add(new javafx.util.Pair<>("北京", "北京市"));
                add(new javafx.util.Pair<>("天津", "天津市"));
                add(new javafx.util.Pair<>("河北", "石家庄市"));
                add(new javafx.util.Pair<>("河北", "邯郸市"));
                add(new javafx.util.Pair<>("湖南", "张家界市"));
                add(new javafx.util.Pair<>("云南", "大理白族自治州"));
                add(new javafx.util.Pair<>("云南", "德宏傣族景颇族自治州"));
                add(new javafx.util.Pair<>("西藏", "日喀则地区"));
                add(new javafx.util.Pair<>("西藏", "林芝地区"));
                add(new javafx.util.Pair<>("陕西", "商洛市"));
                add(new javafx.util.Pair<>("甘肃", "甘南藏族自治州"));
                add(new javafx.util.Pair<>("青海", "海西蒙古族藏族自治州"));
                add(new javafx.util.Pair<>("青海", "黄南藏族自治州"));
                add(new javafx.util.Pair<>("新疆", "克拉玛依市"));
                add(new javafx.util.Pair<>("新疆", "阿克苏地区"));
                add(new javafx.util.Pair<>("新疆", "克孜勒苏柯尔克孜自治州"));
                add(new javafx.util.Pair<>("新疆", "博尔塔拉蒙古自治州"));
                add(new javafx.util.Pair<>("新疆", "图木舒克市"));
                add(new javafx.util.Pair<>("新疆", "铁门关市"));
                add(new javafx.util.Pair<>("宁夏", "吴忠市"));


                add(new javafx.util.Pair<>("新疆", "银川市"));
                add(new javafx.util.Pair<>("陕西", "博尔塔拉蒙古自治州"));
                add(new javafx.util.Pair<>("贵州", "昆明市"));
                add(new javafx.util.Pair<>("", ""));
                add(new javafx.util.Pair<>("adawd12", "!#$@fsk"));
                add(new javafx.util.Pair<>("asfqwqw", ""));
                add(new javafx.util.Pair<>("陕西 ", "榆林市"));
                add(new javafx.util.Pair<>("陕西", " 榆林市"));
            }
        };
        String path = UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/../UnitTest.txt";
        try
                /*(
                        FileWriter rw = new FileWriter(path);
                        PrintWriter pw = new PrintWriter(rw);
                )*/
        {
            OutputStreamWriter ow = new OutputStreamWriter(
                    new FileOutputStream(path),
                    Charset.forName("UTF-8").newEncoder()
            );
            for ( Pair<String, String> p : vec )
            {
                String district = geomap.getDefaultDistrict(p.getKey(), p.getValue());
                String str = p.getKey() + " " + p.getValue() + ": " + ((district == null || district.isEmpty()) ? "----------------" : district);
                //pw.println(str);
                ow.write(str + "\r\n");
            }
            ow.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // get the geo location, if not, then get the default location
    private static void test_10()
    {
        //Tool tool = new Tool();
        System.out.println("----------------begin----------------");
        Vector<ArrayList<String>> addresses = new Vector<ArrayList<String>>()
        {
            {
                add(new ArrayList<String>(Arrays.asList("安徽", "合肥市", "蜀山区", "经开区书香路71号报业园24号楼712")));
                add(new ArrayList<String>(Arrays.asList("内蒙古", "呼和浩特市", "新城区", "呼和浩特市团结小区东区内蒙直属机关宿舍楼3-4-6号")));
                add(new ArrayList<String>(Arrays.asList("宁夏省", "银川", "兴庆区", "凤凰北街中瀛御景小区15号楼二单元502室")));
            }
        };

        for ( ArrayList<String> arrayList : addresses )
        {
            String address = "";
            String result = "";
            for ( String item : arrayList )
            {
                address += item;
            }
            if ( !address.isEmpty() )
            {
                String response = Tool.getGEOLocation(address);
                System.out.println("Map amazon address:" + response);
                if ( response != null )
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if ( jsonObject.has("province") ) result += jsonObject.getString("province") + jsonObject.getString("city") + jsonObject.getString("district");
                }
            }
            System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
            System.out.println("-----------------end-----------------");
        }
    }

    // get the geo location, if not, then get the default location
    private static void test_11()
    {
        //Tool tool = new Tool();

        Vector<ArrayList<String>> addresses = new Vector<ArrayList<String>>()
        {
            {
                add(new ArrayList<String>(Arrays.asList("安徽", "合肥市", "蜀山区", "经开区书香路71号报业园24号楼712")));
                add(new ArrayList<String>(Arrays.asList("安徽", "淮南市", "田家庵区", "上东锦城商铺41-43号中国银行")));
                add(new ArrayList<String>(Arrays.asList("北京", "北京市", "海淀区", "苏州街33号公寓501室")));
                add(new ArrayList<String>(Arrays.asList("福建", "厦门市", "思明区", "文塔路92号2003")));
                add(new ArrayList<String>(Arrays.asList("福建", "泉州市", "晋江市", "罗山街道晋江市看守所")));
                add(new ArrayList<String>(Arrays.asList("甘肃", "兰州市", "七里河区", "甘肃省兰州市七里河区兰工坪287号 兰州理工大学")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "天河区", "天强路2号天祥阁703")));
                add(new ArrayList<String>(Arrays.asList("广西", "南宁市", "青秀区", "佛子岭路23号宁铁馨苑4栋一单元101")));
                add(new ArrayList<String>(Arrays.asList("贵州", "贵阳市", "南明区", "东山中天世纪新城半山居A1栋3单元201室")));
                add(new ArrayList<String>(Arrays.asList("海南", "海口市", "龙华区", "龙昆南路30号 正大豪庭3栋1101")));
                add(new ArrayList<String>(Arrays.asList("广东", "潮州市", "", "潮安区浮洋镇浮洋镇乌洋村乌南路中段潮汕路往凤塘方向150米映鑫竹木厂")));
                add(new ArrayList<String>(Arrays.asList("广东", "潮州市", "", "广东省潮州市湘桥区其他街道西荣春光上铺市场")));
                add(new ArrayList<String>(Arrays.asList("广东", "潮州市", "", "湘桥区东兴南路龙宝集团后楼101代收（501推）")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "", "广东广州市白云区,钟落潭镇长钟路四号")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "", "广东广州市从化市,从化市城郊医院")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "", "广东广州市花都区,花东镇大东村十队伟杰公司")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "", "广东广州市天河区,嘉禾望岗西岭南路2号大岭南工业区B栋3楼良美国际")));
                add(new ArrayList<String>(Arrays.asList("广东", "广州市", "", "潮安区龙湖镇东升村")));
            }
        };

        for ( ArrayList<String> arrayList : addresses )
        {
            String address = "";
            String result = "";
            for ( String item : arrayList )
            {
                address += item;
            }
            if ( !address.isEmpty() )
            {
                String response = Tool.getGEOLocation(address);
                System.out.println("Map amazon address:" + response);
                if ( response != null )
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if ( jsonObject.has("province") ) result += jsonObject.getString("province") + jsonObject.getString("city") + jsonObject.getString("district");
                }
            }
            System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
            System.out.println("-----------------end-----------------");
        }
    }

    private static void test_12()
    {
        //Tool tool = new Tool();

        Vector<ArrayList<String>> addresses = new Vector<ArrayList<String>>()
        {
            {
                add(new ArrayList<String>(Arrays.asList("甘肃", "嘉峪关市", "", "人民政府")));
                add(new ArrayList<String>(Arrays.asList("甘肃", "嘉峪关市", "嘉峪关市", "人民政府")));
                add(new ArrayList<String>(Arrays.asList("甘肃", "嘉峪关市", "嘉峪关市市辖区", "人民政府")));
                add(new ArrayList<String>(Arrays.asList("甘肃", "", "嘉峪关市市辖区", "人民政府")));
            }
        };

        for ( ArrayList<String> arrayList : addresses )
        {
            String address = "";
            String result = "";
            for ( String item : arrayList )
            {
                address += item;
            }
            if ( !address.isEmpty() )
            {
                String response = Tool.getGEOLocation(address);
                System.out.println("Map amazon address:" + response);
                if ( response != null )
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if ( jsonObject.has("province") ) result += jsonObject.getString("province") + jsonObject.getString("city") + jsonObject.getString("district");
                }
            }
            System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
            System.out.println("-----------------end-----------------");
        }
    }

    private static void test_12_()
    {
        Config.setTestTable("1st_test_result");
        HSSFWorkbook workbook = Config.getDB();
        if ( workbook != null )
        {
            HSSFSheet table = workbook.getSheet(Config.TABLE);
            if ( table != null )
            {
                Iterator<Row> rowIt = table.iterator();
                if ( rowIt.hasNext() ) rowIt.next();
                int i = 0;
                System.out.println(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                try
                        (
                                FileWriter fw = new FileWriter(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                                PrintWriter pw = new PrintWriter(fw);
                        )
                {
                    while ( rowIt.hasNext() )
                    {
                        System.out.println("----------------begin----------------");
                        pw.println("----------------begin----------------");
                        Row row = rowIt.next();
                        String address = "";
                        String result = "";
                        for ( Cell cell : row )
                        {
                            address += cell.toString().trim();
                        }
                        if ( !address.isEmpty() )
                        {
                            String response = Tool.getGEOLocationForTest_12_(address);
                            System.out.println("Map amazon address:" + response);
                            pw.println("Map amazon address:" + response);
                            if ( response != null )
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                if ( jsonObject.has("province") ) result += jsonObject.getString("province") + jsonObject.getString("city") + jsonObject.getString("district");
                                if ( jsonObject.has("district") )
                                {
                                    Cell newCell = row.createCell(row.getLastCellNum());
                                    newCell.setCellValue(jsonObject.getString("district"));
                                }
                            }
                        }
                        System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        System.out.println("-----------------end-----------------");
                        pw.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        pw.println("-----------------end-----------------");
                    }
                    pw.close();
                    fw.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            //FileWriter fw = new FileWriter(Config.getPath());
            try
            {
                FileOutputStream out = new FileOutputStream(Config.getAbsolutePath());
                workbook.write(out);
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void test_13()
    {
        Config.setTestTable("2nd_test_result");
        HSSFWorkbook workbook = Config.getDB();
        if ( workbook != null )
        {
            HSSFSheet table = workbook.getSheet(Config.TABLE);
            if ( table != null )
            {
                Iterator<Row> rowIt = table.iterator();
                if ( rowIt.hasNext() ) rowIt.next();
                int i = 0;
                System.out.println(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                try
                        (
                                FileWriter fw = new FileWriter(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                                PrintWriter pw = new PrintWriter(fw);
                        )
                {
                    while ( rowIt.hasNext() )
                    {
                        System.out.println("----------------begin----------------");
                        pw.println("----------------begin----------------");
                        Row row = rowIt.next();
                        String address = "";
                        String result = "";
                        for ( Cell cell : row )
                        {
                            address += cell.toString().trim();
                        }
                        if ( !address.isEmpty() )
                        {
                            String response = Tool.getGEOLocation(address);
                            System.out.println("Map amazon address:" + response);
                            pw.println("Map amazon address:" + response);
                            if ( response != null )
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                if ( jsonObject.has("province") ) result += jsonObject.getString("province") + jsonObject.getString("city") + jsonObject.getString("district");
                                if ( jsonObject.has("district") )
                                {
                                    Cell newCell = row.createCell(row.getLastCellNum());
                                    newCell.setCellValue(jsonObject.getString("district"));
                                }
                            }
                        }
                        System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        System.out.println("-----------------end-----------------");
                        pw.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        pw.println("-----------------end-----------------");
                    }
                    pw.close();
                    fw.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            //FileWriter fw = new FileWriter(Config.getPath());
            try
            {
                FileOutputStream out = new FileOutputStream(Config.getAbsolutePath());
                workbook.write(out);
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
    * province city district MappedProvince MappedCity MappedDistrict DefaultDistrict
    *
    *
    *
    * */
    private static void test_14()
    {
        HSSFWorkbook workbook = Config.getDB();
        System.out.println(workbook == null ? "xls null" : "not");
        if ( workbook != null )
        {
            HSSFSheet table = workbook.getSheet(Config.TABLE);
            System.out.println(workbook == null ? Config.getTable() + " null" : "not");
            if ( table != null )
            {
                Iterator<Row> rowIt = table.iterator();
                if ( rowIt.hasNext() ) rowIt.next();
                int i = 0;
                System.out.println(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                try
                        (
                                FileWriter fw = new FileWriter(UnitTest.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/" + Config.DB_RESOURCE + "/" + Config.TABLE + ".log");
                                PrintWriter pw = new PrintWriter(fw);
                        )
                {
                    while ( rowIt.hasNext() )
                    {
                        System.out.println("----------------begin----------------");
                        pw.println("----------------begin----------------");
                        Row row = rowIt.next();
                        String address = "";
                        String result = "";
                        for ( Cell cell : row )
                        {
                            address += cell.toString().trim();
                        }
                        if ( !address.isEmpty() )
                        {
                            HashMap<String, HashMap<String, String>> response = Tool.getGEOLocationPrivateForTest_14(address);
                            System.out.println(result = ("Map amazon address:" + response));
                            pw.println("Map amazon address:" + response);
                            if ( response != null )
                            {
                                int index = row.getLastCellNum();
                                System.out.println("cell number " + index);
                                Vector<String> sources = new Vector<>(Arrays.asList("GEOAli", "GEOBaidu"));
                                for (int j = 0; j < sources.size(); j++ )
                                {
                                    HashMap<String, String> ali = response.get(sources.get(j));
                                    Cell cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("province"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("city"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("district"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("MappedProvince"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("MappedCity"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("MappedDistrict"));
                                    cell = row.createCell(index++);
                                    cell.setCellValue(ali.get("DefaultDistrict"));
                                }
                            }
                        }
                        System.out.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        System.out.println("-----------------end-----------------");
                        pw.println("Original address:" + address + ", Returned Amazon:" + result + "---");
                        pw.println("-----------------end-----------------");
                    }
                    pw.close();
                    fw.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            //FileWriter fw = new FileWriter(Config.getPath());
            try
            {
                FileOutputStream out = new FileOutputStream(Config.getAbsolutePath());
                workbook.write(out);
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void vectorAndArrayListInitialization()
    {
        Vector<ArrayList<String>> addresses = new Vector<ArrayList<String>>()
        {
            {
                add(new ArrayList<String>()
                {
                    {
                        add("");
                        add("");
                        add("");
                        add("");
                    }
                });
                add(new ArrayList<String>()
                {
                    {
                        add("");
                        add("");
                        add("");
                        add("");
                    }
                });
                add(new ArrayList<String>()
                {
                    {
                        add("");
                        add("");
                        add("");
                        add("");
                    }
                });
                add(new ArrayList<String>()
                {
                    {
                        add("");
                        add("");
                        add("");
                        add("");
                    }
                });
                add(new ArrayList<String>()
                {
                    {
                        add("");
                        add("");
                        add("");
                        add("");
                    }
                });
            }
        };

        Vector<ArrayList<String>> addresses_ = new Vector<ArrayList<String>>()
        {
            {
                add(new ArrayList<String>(Arrays.asList("", "", "", "")));
                add(new ArrayList<String>(Arrays.asList("", "", "", "")));
            }
        };

        Vector<ArrayList<String>> addresses_1 = new Vector<ArrayList<String>>(
                Arrays.asList(
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", "")),
                        new ArrayList<String>(Arrays.asList("", "", "", ""))
                )
        );
    }

    /*private class Pair
    {
        String first;
        String second;
        public Pair()
        {
            first = null;
            second = null;
        }
        public Pair(String f, String s)
        {
            first = f;
            second = s;
        }
    }*/
}
