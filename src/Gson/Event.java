package Gson;

import java.util.List;

/**
 * Created by xuch on 7/11/16.
 */
public abstract class Event
{
    String eventType;
    String eventId;
    List<EventContext> eventContexts;

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventId()
    {
        return eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public List<EventContext> getEventContexts()
    {
        return eventContexts;
    }

    public void setEventContexts(List<EventContext> eventContexts)
    {
        this.eventContexts = eventContexts;
    }
}
