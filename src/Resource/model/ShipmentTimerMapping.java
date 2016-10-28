package Resource.model;

import com.amazon.psrDao.model.TableName.TableNames;
import com.amazon.psrDao.model.utils.DateMarshallar;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.Date;

/**
 * @author xuch
 */
@Data
@DynamoDBTable(tableName = TableNames.SHIPMENT_TIMER_MAPPING)
public class ShipmentTimerMapping {
    public static final String TIMER_ID = "TIMER_ID";
    public static final String SCHEDULED_TIME = "SCHEDULED_TIME";
    public static final String SHIPMENT_ID = "SHIPMENT_ID";
    public static final String SHIPMENT_ID_EVENT_CODE = "SHIPMENT_ID_EVENT_CODE";
    public static final String STATUS = "STATUS";
    public static final String GSI_SHIPMENT_ID = "GSI_SHIPMENT_ID";
    public static final String GSI_SHIPMENT_ID_EVENT_CODE = "GSI_SHIPMENT_ID_EVENT_CODE";


    @DynamoDBHashKey(attributeName = TIMER_ID)
    private String timerId;

    @DynamoDBAttribute(attributeName = SCHEDULED_TIME)
    @DynamoDBMarshalling(marshallerClass = DateMarshallar.class)
    private Date scheduledTime;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = GSI_SHIPMENT_ID, attributeName = SHIPMENT_ID)
    private String shipmentId;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = GSI_SHIPMENT_ID_EVENT_CODE, attributeName = SHIPMENT_ID_EVENT_CODE)
    private String shipmentIdEventCode;

    @DynamoDBAttribute(attributeName = STATUS)
    private String status;

    public ShipmentTimerMapping clone() {
        ShipmentTimerMapping shipmentTimerMapping = new ShipmentTimerMapping();
        shipmentTimerMapping.setTimerId(timerId);
        shipmentTimerMapping.setScheduledTime(scheduledTime);
        shipmentTimerMapping.setShipmentId(shipmentId);
        shipmentTimerMapping.setShipmentIdEventCode(shipmentIdEventCode);
        shipmentTimerMapping.setStatus(status);
        return shipmentTimerMapping;
    }
}
