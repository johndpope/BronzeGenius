package Gson;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xuch on 7/11/16.
 */
public class NotificationEvent extends Event
{
    String notificationId;
    String notificationChannel;
    Address notificationAddress;

    public String getNotificationId()
    {
        return notificationId;
    }

    public void setNotificationId(String notificationId)
    {
        this.notificationId = notificationId;
    }

    public String getNotificationChannel()
    {
        return notificationChannel;
    }

    public void setNotificationChannel(String notificationChannel)
    {
        this.notificationChannel = notificationChannel;
    }

    public Address getNotificationAddress()
    {
        return notificationAddress;
    }

    public void setNotificationAddress(Address notificationAddress)
    {
        this.notificationAddress = notificationAddress;
    }

    private AddressInfo buildAddressInfo(Address address)
    {
        if (address == null) return null;
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddressDetail(address.getAddressId() + "," + address.getAddressDetail() + "," +
                address.getLatitude() + "," + address.getLongitude());
        return addressInfo;
    }

    private List<AddressInfo> fillAddressInfos()
    {
        List<Address> addresses = new LinkedList<Address>();
        addresses.add(notificationAddress);
        Address notificationAddress1 = new Address("Hello World");
        notificationAddress1.setLatitude(99999900.1232);
        notificationAddress1.setLongitude(12421421.2998);
        addresses.add(notificationAddress1);

        List<AddressInfo> addressInfoList = addresses.stream().map(this::buildAddressInfo).collect(Collectors.toList());
        return addressInfoList;
    }

    public static void main(String args[])
    {
        Object ob = getInstance(EventContext.class);
        System.out.println(ob instanceof  AddressInfo ? "yes" : "no");
        System.exit(1);



        NotificationEvent event = new NotificationEvent();
        NotificationEvent notificationEvent = event.getEvent();
        event.setEventId("22");
        event.setEventType("Failure");
        event.setNotificationId("PP11");
        event.setNotificationChannel("SQS");

        Address notificationAddress = new Address();
        notificationAddress.setLongitude(12321);
        notificationAddress.setLatitude(24234);
        notificationAddress.setAddressDetail("Canada");
        event.setNotificationAddress(notificationAddress);

        List<EventContext> eventlst = new ArrayList<EventContext>();
        EventContext context = new EventContext();
        context.setShipToAddress(new Address("To Address"));
        context.setShipFromAddress(new Address("From Address"));
        eventlst.add(context);
        event.setEventContexts(eventlst);

        List<AddressInfo> addressInfos = event.fillAddressInfos();
        addressInfos.forEach((i) -> System.out.println(i.getAddressDetail()));
        System.exit(1);

        Gson gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(null, null);
        String result = gson.toJson(event, NotificationEvent.class);
        System.out.println(result);
        Type type = new TypeToken<NotificationEvent>() {}.getType();
        NotificationEvent ev = gson.fromJson(result, type);
        System.out.println(ev.getNotificationChannel());

        event.setNotificationChannel("Hello Wolrd");
        System.out.println(ev.getNotificationChannel());

        /*String serialized = gson.toJson(notificationEvent, NotificationEvent.class);
        System.out.println(serialized);
        System.out.println(notificationEvent.getNotificationAddress().getAddressDetail());

        Type fooType = new TypeToken<NotificationEvent>() {}.getType();
        String json = gson.toJson(notificationEvent, fooType);
        System.out.println(json);

        Event et = gson.fromJson(json, fooType);
        //System.out.println(et.getEventContexts().get(0).getShipFromAddress().getAddressDetail());

        NotificationEvent deEvent = gson.fromJson(result, NotificationEvent.class);
        System.out.println(deEvent.getEventContexts().get(0).getShipFromAddress().getAddressDetail());*/
    }

    private static Object getInstance(Class t)
    {
        if (t == AddressInfo.class) return new AddressInfo();
        else if (t == EventContext.class) return new EventContext();
        return null;
    }

    private NotificationEvent getEvent()
    {
        NotificationEvent notificationEvent = new NotificationEvent()
        {
            {
                setEventId("0001");
                setEventType("ExecutionEvent");
                setEventContexts(new ArrayList<EventContext>()
                {
                    {
                        add(new EventContext()
                        {
                            {
                                setShipmentId("S1");
                                setShipDate(new Date());
                                setShipFromAddress(new Address()
                                {
                                    {
                                        setAddressId("Addr001");
                                        setAddressDetail("Winterfall, Black Wall");
                                        setLatitude(132.099291929);
                                        setLongitude(84.342498134);
                                    }
                                });
                                setShipToAddress(new Address()
                                {
                                    {
                                        setAddressId("Addr002");
                                        setAddressDetail("King's Road, Steel Island");
                                        setLatitude(132.099291929);
                                        setLongitude(84.342498134);
                                    }
                                });
                            }
                        });
                        add(new EventContext()
                        {
                            {
                                setShipmentId("S2");
                                setShipDate(new Date());
                                setShipFromAddress(new Address()
                                {
                                    {
                                        setAddressId("Addr11119");
                                        setAddressDetail("Caster Rock, West");
                                        setLatitude(132.099291929);
                                        setLongitude(84.342498134);
                                    }
                                });
                                setShipToAddress(new Address()
                                {
                                    {
                                        setAddressId("Addr22229");
                                        setAddressDetail("Cater Vallet, Twins Tower");
                                        setLatitude(132.099291929);
                                        setLongitude(84.342498134);
                                    }
                                });
                            }
                        });
                    }
                });
                setNotificationId("NT20160712");
                setNotificationChannel("SQS");
                setNotificationAddress(new Address()
                {
                    {
                        setAddressId("QueueUrl");
                        setAddressDetail("https://sqs.cn-north-1.amazonaws.com.cn/463827642039/SHIPPING-DOCUMENT-CREATION-CN-BETA-SQS");
                        setLatitude(132.099291929);
                        setLongitude(84.342498134);
                    }
                });
            }
        };
        return notificationEvent;
    }
}