package HelloWorld;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import javafx.beans.binding.ObjectExpression;
import lombok.Data;
import org.apache.commons.exec.util.MapUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.JodaTimePermission;
import org.junit.Assert;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.FileSystemNotFoundException;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.Exception;
import java.util.stream.Collectors;

import javafx.util.Pair;

import org.joda.time.DateTime;
import static org.mockito.Mockito.*;

/**
 * Created by xuch on 4/28/16.
 */
public class HelloWorld
{
    private static Gson gson = new GsonBuilder().create();

    @Data
    public class TestType {
        private Test type;
    }

    public enum Test {
        @SerializedName(value = "HELLO", alternate = {"Hello", "hello"})
        HELLO("haha");
        private String key;
        Test(String key) {
            this.key = key;
        }
        public String toString() {
            return key;
        }
    }

    /**
     * http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
     * http://stackoverflow.com/questions/9856195/how-to-read-an-http-input-stream
     * @param url
     * @return
     */
    public static String getResponse(String url) {
        try {
            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
            request.connect();
            System.out.println(request.getContent());
            System.out.println(request.toString());
            JsonElement root = new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
            //System.out.println(root.getAsJsonObject());
            //System.out.println(root.toString());
            Scanner s = new Scanner(new URL(url).openStream()).useDelimiter("\\A");


            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(out);
            String result = s.hasNext() ? s.next() : null;
            System.out.println(result);
            new JsonParser().parse(result);
            //InputStreamReader in = new InputStreamReader((InputStream) request.getContent())
            return root.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Validator validator;

    public static void testsHere() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
                //.registerTypeAdapter(new TypeToken<ArrayList<String>>(){}.getType(), new FoodItemsTypeAdapter())
                //.registerTypeAdapter(((List<String>)new ArrayList<String>()).getClass(), new FoodItemsTypeAdapter())
                .registerTypeAdapter(new TypeToken<List<String>>(){}.getType(), new FoodItemsTypeAdapter())
                .registerTypeAdapter(Coordinates.class, new CoordinatesAdapter())
                //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        // {"coordinates":[43.0923923224, -123.4527009643]}
        String json = "[{\"DescriptiON\":\"details\",\"name\":\"Sex Beach Party\",\"coordinates\":[43.0923923224, -123.4527009643],\"eventDate\":\"2017-02-25T23:31:32.243\",\"eventLocation\":{\"address\":\"CA, United States\",\"latitude\":45.76908908092343,\"longitude\":-123.98692471920307},\"foodItems\":\"Chicken: Sausage: Champagne\"},{\"DescriptiON\":\"details\",\"name\":\"Sex Beach Party\",\"coordinates\":[43.9999999999, -123.9999999999],\"eventDate\":\"2017-02-25T23:31:32.243\",\"eventLocation\":{\"address\":\"CA, United States\",\"latitude\":45.99999999999999,\"longitude\":-123.99999999999999},\"foodItems\":\"Chicken: Sausage: Champagne\"}]";

        List<PartyEvent> newPartyList = gson.fromJson(json, new TypeToken<List<PartyEvent>>(){}.getType());
        PartyEvent newParty = newPartyList.get(1);
        System.out.println(newPartyList.get(0).getCoordinates().getLatitude() + "**" + newPartyList.get(0).getCoordinates().getLongitude());
        System.out.println(newParty.getCoordinates().getLatitude() + "**" + newParty.getCoordinates().getLongitude());
        System.exit(0);


        Location eventLocation = new Location();
        eventLocation.setAddress("CA, United States");
        eventLocation.setLatitude(45.76908908092343);
        eventLocation.setLongitude(-123.98692471920307);

        List<String> foodItems = new ArrayList<String>();
        foodItems.add("Chicken");
        foodItems.add("Sausage");
        foodItems.add("Champagne");

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(44.0000222003);
        coordinates.setLongitude(122.0000222003);
        PartyEvent partyEvent = new PartyEvent();
        partyEvent.setName("Sex Beach Party");
        partyEvent.setDescription("details");
        partyEvent.setEventDate(new DateTime());
        partyEvent.setEventLocation(eventLocation);
        partyEvent.setFoodItems(foodItems);
        partyEvent.setCoordinates(coordinates);

        System.out.println(gson.toJson(partyEvent));
    }

    public static void streamTest() {
        Coordinates coordinates1 = new Coordinates();
        coordinates1.setLatitude(111.00);
        coordinates1.setLongitude(111.00);

        Coordinates coordinates2 = new Coordinates();
        coordinates2.setLatitude(222.00);
        coordinates2.setLongitude(222.00);

        PriorityQueue<Pair<Double, Coordinates>> queue = new PriorityQueue<>((Pair<Double, Coordinates> first, Pair<Double, Coordinates> second) -> first.getKey().compareTo(second.getKey()));
        queue.add(new Pair<Double, Coordinates>(90.00, coordinates1));
        queue.add(new Pair<Double, Coordinates>(40.00, coordinates2));

        List<Coordinates> result = queue.stream().filter(each -> each.getKey().compareTo(80.00) < 1).map(each -> each.getValue()).collect(Collectors.toList());

        queue.forEach(each -> System.out.println(each.getKey() + ", " + each.getValue()));
        result.forEach(each -> System.out.println(each.getLatitude()));

    }

    public static void testGson() {
        String nullStr = null;
        String emptyStr = "";
        TestType testType = gson.fromJson(nullStr, TestType.class);
        TestType testType1 = gson.fromJson(emptyStr, TestType.class);

        System.out.println(testType == null ? "null" : "not null");
        System.out.println(testType1 == null ? "null" : "not null");
    }

    public static void testStatic() {
        Location location = new Location();
        Coordinates coordinates = Constant.getInitialCoordinates(null);
        location.setLatitude(coordinates.getLatitude());
        location.setLongitude(coordinates.getLongitude());
        System.out.println(gson.toJson(location));
    }

    public static void testPriorityQueue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(5);
        priorityQueue.add(0);
        priorityQueue.add(58);
        priorityQueue.add(-1);
        priorityQueue.add(2);

        Object[] list = priorityQueue.toArray();
        for (Integer each : priorityQueue) {
            System.out.println(each);
        }

        for (Object each : list) {
            System.out.println((Integer)each);
        }


        List<Pair<Double, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>(10.0, ""));
        pairList.add(new Pair<>(-1.0, ""));
        pairList.add(new Pair<>(1.0, ""));
        pairList.add(new Pair<>(100.0, ""));
        pairList.add(new Pair<>(100.0, ""));
        pairList.add(new Pair<>(10.0, ""));

        pairList.sort(new Comparator<Pair<Double, String>>() {
            @Override
            public int compare(Pair<Double, String> o1, Pair<Double, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        pairList.forEach(each -> System.out.println(each.getKey()));

    }

    public static void testJodaTime() {
        DateTime now = new DateTime();
        System.out.println(now.getMillis());
        //now = now.plusMillis(1000);

        now.plus(1000);
        System.out.println(now.isAfter(now));
        System.out.println(now.plus(1000).isAfter(now));
    }

    public static void testValidate() {
        Coordinates coordinates = new Coordinates();
        //Validate.notBlank(coordinates);
    }

    public static void testException() {
        try {
            throw new StringValidationException();
        } catch (StringValidationException e) {
            System.out.println("Catches a StringValidationException");
        } catch (ValidationException e) {
            System.out.println("Catches a ValidationException");
        }
    }

    public static void main(String[] args) throws Exception
    {
        testException();
        System.exit(0);


        Map<String, Integer> hm = ImmutableMap.of("a", 1).of("b", 2);
        hm.forEach((k, v) -> System.out.println(k + ":" + v));

        Map<String, Integer> hm1 = ImmutableMap.of("a", 1, "b", 2);
        hm1.forEach((k, v) -> System.out.println(k + ":" + v));
        System.exit(0);

        System.out.println(Integer.reverse(8));
        testJodaTime();
        System.exit(0);

        //testPriorityQueue();
        //testStatic();
        //System.exit(0);
        if (Integer.MAX_VALUE != (long)Integer.MAX_VALUE) System.out.println("not equal");
        System.out.println(((long)Integer.MAX_VALUE) * 60 * 60 * 60);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);

        double dMax = 2147483650d;
        double dMin = -2147483650d;
        System.out.println(new Double(dMax).intValue());
        System.out.println(new Double(dMin).intValue());

        double dCeil = Math.ceil(Double.MAX_VALUE);
        System.out.println(dCeil);
        testGson();


        Test testEnum = Test.HELLO;
        Test testEnumNull = null;
        System.out.println(Status.BeforeArrivedPickup);
        //System.out.println(testEnumNull.name());
        System.out.println(String.valueOf(new Double(111.00)));
        //streamTest();
        System.exit(0);


        String gg = "$";
        List<String> ttt = new ArrayList<>();
        List<String> sll = Arrays.asList("a", "b", "c");
        sll.forEach(a->{
            Integer length = a.length();
            if (length == 1) ttt.add(gg.concat(a));
        });
        System.out.println(ttt.toString());
        //System.out.println(MessageForma);

//        System.out.println(HelloWorld.class.getResource("out").getFile());
//        System.out.println(HelloWorld.class.getResource("test.txt").getFile());
//        System.out.println(HelloWorld.class.getResourceAsStream("test.txt"));
//        System.out.println(HelloWorld.class.getResourceAsStream("/HelloWorld/test.txt"));
//        System.out.println(HelloWorld.class.getResourceAsStream("/output.txt"));
//        System.exit(0);

        testsHere();
        System.exit(0);

        String coordinatesJson = "{\"coordinates\":[43.0923923224, -123.4527009643]}";
        JsonElement jsonElement = new JsonParser().parse(coordinatesJson);
        JsonArray jsonArray = jsonElement.getAsJsonObject().get("coordinates").getAsJsonArray();

        System.out.println();
        System.out.println(jsonArray.toString());
        System.exit(0);

        List<String> l1 = Arrays.asList("a", "b", "c");
        List<String> l2 = Arrays.asList("a", "b", "c");

        List<Integer> l3 = Arrays.asList(0, 2, 3);
        List<Integer> l4 = Arrays.asList(1, 2, 3);
        Assert.assertArrayEquals(l3.toArray(), l4.toArray());
        System.exit(0);

        //{"type":"Point"}
        // [-122.492677,37.74255]
        System.out.println(new Gson().toJson(Arrays.asList(183.90, 243.092)));
        System.out.println(new Gson().toJson(Test.HELLO));
        TestType testType = new Gson().fromJson("{\"type\":\"hello\"}", TestType.class);
        List<Double> result = new Gson().fromJson("[-122.492677,37.74255]", ArrayList.class);
        System.out.println(result.get(0) + "***" + result.get(1));
        System.out.println(testType.getType().name());
        System.exit(0);


        System.out.println(getResponse("https://data.sfgov.org/resource/6a9r-agq8.json"));
        System.exit(0);


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
                //.registerTypeAdapter(new TypeToken<ArrayList<String>>(){}.getType(), new FoodItemsTypeAdapter())
                //.registerTypeAdapter(((List<String>)new ArrayList<String>()).getClass(), new FoodItemsTypeAdapter())
                .registerTypeAdapter(new TypeToken<List<String>>(){}.getType(), new FoodItemsTypeAdapter())
                //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        Location eventLocation = new Location();
        eventLocation.setAddress("CA, United States");
        eventLocation.setLatitude(45.76908908092343);
        eventLocation.setLongitude(-123.98692471920307);

        List<String> foodItems = new ArrayList<String>();
        foodItems.add("Chicken");
        foodItems.add("Sausage");
        foodItems.add("Champagne");
        PartyEvent partyEvent = new PartyEvent();
        partyEvent.setName("Sex Beach Party");
        partyEvent.setDescription("details");
        partyEvent.setEventDate(new DateTime());
        partyEvent.setEventLocation(eventLocation);
        partyEvent.setFoodItems(foodItems);

        System.out.println(gson.toJson(partyEvent));

        //"foodItems":"Chicken: Sausage: Champagne"
        //"foodItems":["Chicken","Sausage","Champagne"]

        String json = "{\"DescriptiON\":\"details\",\"name\":\"Sex Beach Party\",\"eventDate\":\"2017-02-25T23:31:32.243\",\"eventLocation\":{\"address\":\"CA, United States\",\"latitude\":45.76908908092343,\"longitude\":-123.98692471920307},\"foodItems\":\"Chicken: Sausage: Champagne\"}";

        PartyEvent newParty = gson.fromJson(json, PartyEvent.class);
        System.out.println(newParty.getName() + ", " + newParty.getDescription());
        System.out.println(newParty.getEventDate().toString());
        System.out.println(newParty.getEventLocation().toString() );
        System.out.println(newParty.getFoodItems().toString());


        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()));
        //System.out.println(getResponse("https://data.sfgov.org/resource/6a9r-agq8.json"));
        Date tstDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2015-08-09T00:00:00.000");

        DateTime dateTime = DateTime.parse("2015-08-09T00:00:00.000");
        System.out.println(dateTime.toString());

        dateTime = DateTime.parse("2015-08-09");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(dateTime.toDate()));

        System.exit(0);

        System.out.println(Test.HELLO.name());
        System.out.println(Test.HELLO.toString());

        Map<String, Map<String, String>> stringObjectMap = new HashMap<>();
        stringObjectMap.put("haha", new HashMap<String, String>());
        Map<String, String> stringStringMap = stringObjectMap.get("PickupDone");
        System.out.println("Is " + ( stringStringMap == null ? "empty" : "not empty"));


        String uid1 = "40f59cc08ff156abdd386a096b9b94c381b38461";
        String uid2 = "d5ee90a052ce373a5e612819c49cfd2cfac6a960";
        System.out.println(uid1.length() + "," + uid2.length());
        System.out.println(new Date().getTime());
        Date date = null;
        if (date == null) {
            //throw new FromRuntimeException("from runtime exception");
        }
        //System.out.println(date.toString());
        double a = 1232131241.000034324;
        double b = 9258334.45352467801;
        System.out.println(String.format("hahah%f,sss%fss,", a, b));
        Date eventTime = null;
        String shipmentId = "1111111", eventCode = "WMM", nullStr = null;
        System.out.println(nullStr + null);
        System.out.println(String.format(
                "There are more than one item with the same combination of [shpmentId: %s, eventCode: %s], " +
                        "when querying ShipmentTimerMapping dynamoDB table.",
                shipmentId, eventCode));
        try {
            throw new IllegalArgumentException(String.format(
                    "There are more than one item with the same combination of [shpmentId: %s, eventCode: %s], " +
                            "when querying ShipmentTimerMapping dynamoDB table.",
                    shipmentId, eventTime));
        } catch (IllegalArgumentException e) {
            System.out.println("captures an IllegalArgumentException");
            throw new IllegalStateException("thrown from first catch");
        } catch (Exception e) {
            System.out.println("captures an Exception " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(Long.parseLong("1476849329196"));
        Date now =  new Date();
        System.out.println(now.toString() + "|" + now.getTime());

        System.exit(1);

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

    public Date test() {
        if (validator.validate(true)) return new Date();
        else return new Date((new Date()).getTime() + 1000 * 20);
    }
}