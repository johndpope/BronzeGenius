package Gson;

import java.util.Date;

/**
 * Created by xuch on 7/11/16.
 */
public class EventContext
{
    String shipmentId;
    Date shipDate;
    Address shipFromAddress;
    Address shipToAddress;

    public String getShipmentId()
    {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId)
    {
        this.shipmentId = shipmentId;
    }

    public Date getShipDate()
    {
        return shipDate;
    }

    public void setShipDate(Date shipDate)
    {
        this.shipDate = shipDate;
    }

    public Address getShipFromAddress()
    {
        return shipFromAddress;
    }

    public void setShipFromAddress(Address shipFromAddress)
    {
        this.shipFromAddress = shipFromAddress;
    }

    public Address getShipToAddress()
    {
        return shipToAddress;
    }

    public void setShipToAddress(Address shipToAddress)
    {
        this.shipToAddress = shipToAddress;
    }
}
