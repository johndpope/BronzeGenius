package Resource.model;

import com.amazon.psrDao.model.TableName.TableNames;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

/**
 * @author xuch
 */
@Data
@DynamoDBTable(tableName = TableNames.PSREVENT)
public class PSREvent {
    public static final String TRACKING_ID = "TRACKING_ID";
    public static final String EVENT_CODE_EVENT_TIME = "EVENT_CODE_EVENT_TIME";
    public static final String EVENT_PAYLOAD = "EVENT_PAYLOAD";
    public static final String TIMER_ID = "TIMER_ID";
    public static final String GSI_TIMER_ID = "GSI_TIMER_ID";

    @DynamoDBHashKey(attributeName = TRACKING_ID)
    private String trackingId;

    @DynamoDBRangeKey(attributeName = EVENT_CODE_EVENT_TIME)
    private String eventCodeEventTime;

    @DynamoDBAttribute(attributeName = EVENT_PAYLOAD)
    private String eventPayload;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = GSI_TIMER_ID, attributeName = TIMER_ID)
    private String timerId;

    public PSREvent clone() {
        PSREvent psrEvent = new PSREvent();
        psrEvent.setTrackingId(trackingId);
        psrEvent.setEventCodeEventTime(eventCodeEventTime);
        psrEvent.setEventPayload(eventPayload);
        psrEvent.setTimerId(timerId);
        return psrEvent;
    }

}
