package HelloWorld;

import javafx.beans.binding.ObjectExpression;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.Exception;

/**
 * Created by xuch on 4/28/16.
 */
public class HelloWorld
{
    public static void main(String[] args) throws Exception
    {
        try{throwException();}
        catch (Exception e)
        {
            throw e;//e.printStackTrace();
        }
        System.out.println("done!!");
        System.exit(1);

        String hello = "hello";
        System.out.println("Customer: [DJS]'s request [params: "
                        + hello + "] has wrong format params.");
        Status status1 = Status.ArrivedDestination;
        Status status2 = status1;
        System.out.println(getCNFormatTimestamp("2016-12-16 00:20:00.0"));
        System.out.println(status1.name() + status2.name());

        System.out.println(splitCNTimeString("2017年8月13日 上午12:08"));

        Date CNDate = getTimestampFromString("2016-12-16 12:20:00.0");
        System.out.println(CNDate.toString());
        CNDate = getTimestampFromString("2016-12-16 13:20:00.0");
        System.out.println(CNDate.toString());
        System.out.println((new Date()).toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateFormat.format((new Date())));
        //String nullStr = null;
        //nullStr.trim();
        System.exit(1);

        List<String> strings = null;
        for ( String each : strings )
        {
            System.out.println(each);
        }

        List<String> stringList = new ArrayList<String>()
        {
            {
                add(null);
                add("");
                add("hello");
                add("");
                add("world");
                add(null);
                add("");
                add("morning");
            }
        };

        System.out.println(joinStringArray(stringList, "/"));
        System.exit(1);

        Date aDate = new Date(1461338522);
        Date bDate = null;
        System.out.println(aDate.compareTo(bDate));

        String city = "市";
        System.out.println(city.replaceAll("市$", ""));
        System.exit(1);
        String province = null;
        String location = city + province;
        System.out.println(city + province);
        System.out.println(location);

        Map<String, Object> mp = new HashMap<>();
        mp.put("time", null);
        mp.put("CNTime", getCNFormatTimestamp((String)mp.get("time")));
        System.out.println(mp.get("time") == null ? "null" : "no, " + mp.get("CNTime") == null ? "null" : "no");
        System.exit(0);



        String strstr = "abc23456";
        System.out.println(strstr.matches("[^abc]"));
        System.out.println(strstr.startsWith("abc"));
        Object n = null;
        System.out.println(String.valueOf(n));


        try
        {
            throwException();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Map<String, String> mmp = new HashMap<String, String>()
        {
            {
                put("a", "hello");
                put("b", "world");
            }
        };

        System.out.println(mmp);

        Map<String, Object> mmmp = new HashMap<String, Object>()
        {
            {
                put("int", 1);
                put("color", null);
                put("map", mmp);
            }
        };

        System.out.println(mmmp);

        Timestamp timestamp = null;
        System.out.println(Objects.isNull(timestamp) ? "yes" : "no");
        System.out.println(Objects.nonNull(timestamp) ? "yes" : "no");
        System.out.println(timestamp == null ? "yes" : "no");
        Float f = 188f;
        float f1 = 18.0f;
        Float f2 = f + f1;
        Float f3 = null;
        //System.out.println(f2 + f3);
        System.out.println(f + " + " + f1 + " = " + f2);

        System.out.println(Status.BeforeArrivedPickup.toString());

        Map<String, Object> mpp = new HashMap<>();
        mpp.put("a--", null);
        System.out.println(mpp.get("a"));
        System.exit(0);

        System.out.println(Status.BeforeArrivedPickup.getStatusFlag() < Status.ArrivedPickup.getStatusFlag() ? "yes" : "no");
        System.exit(0);


        String strNull = null;
        String str = "hello";
        String str1 = new String("hello");
        String str2 = new String("HeLLo");
        System.out.println(str.equals(str1) ? "yes" : "no");
        System.out.println(str == str1 ? "yes" : "no");
        System.out.println(str1 == str2 ? "yes" : "no");
        System.out.println(str1.equals(str2) ? "yes" : "no");
        System.out.println(str1.equalsIgnoreCase(str2) ? "yes" : "no");
        System.out.println(str.equals(strNull) ? "yes" : "no");
        System.out.println(str1.equalsIgnoreCase(strNull) ? "yes" : "no");
        //System.out.println(strNull.equals(str) ? "yes" : "no");
        //System.out.println(Status.ArrivedPickup);
        //System.out.println(Status.ArrivedPickup.equals(1) ? "yes" : "no");
    }

    /*
     * 只要发生exception, 要么throw给上层,要么进入catch, exception之后的所有正常逻辑不会执行。
     * 而catch之后的代码,如果不是throw exception,那么catch之后的代码会执行,否则,在catch抛出异常处停止,回到上层。
     * 总结:凡是抛出异常处,即停止执行,要么回到上层,要么进入catch。
     * catch如果继续抛出异常,则同理回到上层或者进入到下一个catch。
     * catch如果不抛出异常,则正常执行catch模块和之后的代码。
     *
     * */
    public static void throwException() throws Exception
    {
        //try
        //{
        if (true) throw new Exception("exception 1");
        Float f1 = null;
        float f2 = 0f + f1;
        //}
        //catch (Exception e)
        //{
        //e.printStackTrace();
        //throw e;
        //}
        System.out.println("end of throwException");
    }

    public static String generatingResponse()
    {

        Map<String, Object> response = new HashMap<>();
        try
        {
            Map<String, Object> result = new HashMap<>();

            response.put("Status", "1");
            response.put("Info", "OK");


        /*{"Status":"1","Info":"OK","Result":{"EvaluatedArrival":null,"EvaluatedDeparture":null,"Arrival":2016-03-16 18:20:00.0,"StatusCode":4,"SignInResult":null,"0":{"EventTime":null,"EventProvince":"北京","EventCountry":"CN","EventCity":"北京"},"PickupResult":null,"1":{"PickupResult":null,"EventTime":null,"EventProvince":"北京","EventCountry":"CN","EventCity":"北京"},"2":{"EventTime":2016-03-16 20:20:00.0,"EventProvince":"北京","EventCountry":"CN","Process":1.0,"EventCity":"北京"},"3":{"EventTime":2016-03-17 16:27:00.0,"EventProvince":"北京","EventCountry":"CN","EventCity":"北京"},"ToCity":"北京","Departure":2016-03-16 20:20:00.0,"StatusInfo":"ArrivedDestination","FromCity":"北京"}}​*/

            int statusCode = 4;
            result.put("StatusCode", statusCode);
            result.put("StatusInfo", "ArrivedDestination");
            result.put("FromCity", "北京");
            result.put("ToCity", "北京");
            result.put("EvaluatedArrival", null);
            result.put("EvaluatedDeparture", null);
            result.put("Arrival", getTimestampFromString("2016-03-16 18:20:00.0"));
            result.put("Departure", getTimestampFromString("2016-03-16 20:20:00.0"));
            result.put("PickupResult", null);
            result.put("SignInResult", null);

            Map<String, Object> node = new HashMap<>();
            node.put("EventTime", null);
            node.put("EventCity", "北京");
            node.put("EventProvince", "北京");
            node.put("EventCountry", "CN");
            result.put("0", node);

            node = new HashMap<>();
            node.put("EventTime", null);
            node.put("EventCity", "北京");
            node.put("EventProvince", "北京");
            node.put("EventCountry", "CN");
            node.put("PickupResult", null);
            result.put("1", node);

            node = new HashMap<>();
            node.put("EventTime", getTimestampFromString("2016-03-16 20:20:00.0"));
            node.put("EventCity", "北京");
            node.put("EventProvince", "北京");
            node.put("EventCountry", "CN");
            node.put("Process", 1f);
            result.put("2", node);

            node = new HashMap<>();
            node.put("EventTime", getTimestampFromString("2016-03-17 16:27:00.0"));
            node.put("EventCity", "北京");
            node.put("EventProvince", "北京");
            node.put("EventCountry", "CN");
            result.put("3", node);

            response.put("Result", result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //return JSONObject.toJSONString(response);
        return "";
    }

    public static Timestamp getTimestampFromString(String str) throws Exception
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = dateFormat.parse(str);
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    public static String getCNFormatTimestamp(String str)
    {
        if ( Objects.nonNull(str) )
        {
            try
            {
                Timestamp time = getTimestampFromString(str);
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int dayOfWeekInMonth = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                int am_pm = cal.get(Calendar.AM_PM);
                int am = cal.get(Calendar.AM);
                int pm = cal.get(Calendar.PM);
                int hour = cal.get(Calendar.HOUR);
                int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                if(am_pm != 0 && hour == 0) hour = 12;
                return year + "年" + (++month) + "月" + day + "日 " + (am_pm == 0 ? "上午" : "下午") + hour + "点" + min + "分";

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String joinStringArray(List<String> stringArray, String seperator)
    {
        if ( stringArray == null || stringArray.isEmpty() ) return null;
        StringBuilder builder = new StringBuilder();
        for ( String s : stringArray ) if ( s != null && !s.isEmpty() ) builder.append(s + seperator);
        return builder.toString().replaceAll(seperator + "$", "");
    }

    public static String splitCNTimeString(String input)
    {
        if ( input != null ) return input.trim().replace(" ", "</br>");
        return null;
    }

    public static void fuck() throws Exception
    {
        String query = String.format(
                "key=%s&address=%s&output=%s",
                URLEncoder.encode("hello", "UTF-8")
        );
        return;
    }
}