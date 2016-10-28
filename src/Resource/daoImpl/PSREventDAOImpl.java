package Resource.daoImpl;

import com.amazon.coral.google.common.collect.ImmutableMap;
import com.amazon.psrDao.dao.PSREventDAO;
import com.amazon.psrDao.model.PSREvent;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author xuch
 */
@Slf4j
public class PSREventDAOImpl extends GenericDAOImpl<PSREvent> implements PSREventDAO {
    private static final String qualifier = "_";

    @Override
    public Class<PSREvent> getPersistantClassName() {
        return PSREvent.class;
    }

    /**
     * Fetch the event as per the primary key consisting of
     * partion key "trackingId" and sort key "eventCode_eventTime".
     * The read should be strongly consistent read.
     * DynamoDBMapper.load() use strongly consistent read.
     */
    @Override
    public PSREvent getPSREvent(String trackingId, String eventCode, Date eventTime) {
        if (StringUtils.isBlank(trackingId) || StringUtils.isBlank(eventCode) || eventTime == null) {
            throw new IllegalArgumentException("Invalid received arguments, trackingId: " + trackingId
                    + ", eventCode: " + eventCode
                    + ", eventTime: " + eventTime);
        }
        PSREvent psrEvent = load(trackingId, generateEventCodeEventTime(eventCode, eventTime));

        return psrEvent;
    }

    /**
     * Fetch the event as per the primary key consisting of
     * partion key "trackingId" and sort key "eventCode_eventTime"
     */
    @Override
    public PSREvent getPSREvent(String trackingId, String eventCodeEventTime) {
        if (StringUtils.isBlank(trackingId) || StringUtils.isBlank(eventCodeEventTime)) {
            throw new IllegalArgumentException("Invalid received arguments, trackingId: " + trackingId
                    + ", eventCodeEventTime: " + eventCodeEventTime);
        }
        PSREvent psrEvent = load(trackingId, eventCodeEventTime);

        return psrEvent;
    }

    /**
     * Fetch all PSREvents when an timer event comes around from the STS
     * and the timer status is SCHEDULED.
     * The read should be strongly consistent read so that the most up-to-date events can be fetched.
     * However query on a GSI cannot work on strongly consistent read.
     */
    @Override
    public List<PSREvent> getPSREvents(String timerId) {
        if (StringUtils.isBlank(timerId)) {
            throw new IllegalArgumentException("Invalid received argument, timerId: " + timerId);
        }
        PSREvent event = new PSREvent();
        event.setTimerId(timerId);
        DynamoDBQueryExpression<PSREvent> queryExpression = new DynamoDBQueryExpression<PSREvent>()
                .withIndexName(PSREvent.GSI_TIMER_ID)
                .withHashKeyValues(event)
                .withConsistentRead(false);
        List<PSREvent> result = query(queryExpression);
        if (result.isEmpty()) {
            log.info("Query result is empty from dynamoDB table PSREvent with timerId: {}", timerId);
        }
        return result;
    }

    /**
     * Update an event associated with a certain timer whose status is "SCHEDULED".
     * Generally it is only used to update the event payload.
     */
    @Override
    public void updatePSREvent(PSREvent psrEvent) {
        if (psrEvent == null) {
            throw new IllegalArgumentException("Null PSREvent cannot be updated");
        }

        if (StringUtils.isBlank(psrEvent.getTimerId())
                || StringUtils.isBlank(psrEvent.getTrackingId())
                || StringUtils.isBlank(psrEvent.getEventCodeEventTime())) {
            throw new IllegalArgumentException("Invalid PSREvent with these attributes, trackingId: "
                    + psrEvent.getTrackingId() + ", eventCodeEventTime: "
                    + psrEvent.getEventCodeEventTime() + ", timerId: "
                    + psrEvent.getTimerId());
        }

        DynamoDBSaveExpression updateExpression = new DynamoDBSaveExpression()
                .withExpected(new ImmutableMap.Builder<String, ExpectedAttributeValue>()
                        .put(PSREvent.TRACKING_ID,
                                new ExpectedAttributeValue().withValue(
                                        new AttributeValue(psrEvent.getTrackingId())).withExists(true))
                        .put(PSREvent.EVENT_CODE_EVENT_TIME,
                                new ExpectedAttributeValue().withValue(
                                        new AttributeValue(psrEvent.getEventCodeEventTime())).withExists(true))
                        .build());
        update(psrEvent, updateExpression,
                new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
    }

    /**
     * Insert an up-coming event ssociated with a certain timer whose status is "SCHEDULED".
     */
    @Override
    public void insertPSREvent(PSREvent psrEvent) {
        if (psrEvent == null) {
            throw new IllegalArgumentException("Null PSREvent cannot be inserted");
        }

        if (StringUtils.isBlank(psrEvent.getTimerId())
                || StringUtils.isBlank(psrEvent.getTrackingId())
                || StringUtils.isBlank(psrEvent.getEventCodeEventTime())) {
            throw new IllegalArgumentException("Invalid PSREvent with these attributes, trackingId: "
                    + psrEvent.getTrackingId() + ", eventCodeEventTime: "
                    + psrEvent.getEventCodeEventTime() + ", timerId: "
                    + psrEvent.getTimerId());
        }

        DynamoDBSaveExpression insertExpression = new DynamoDBSaveExpression()
                .withExpected(new ImmutableMap.Builder<String, ExpectedAttributeValue>()
                        .put(PSREvent.TRACKING_ID, new ExpectedAttributeValue(false))
                        .put(PSREvent.EVENT_CODE_EVENT_TIME, new ExpectedAttributeValue(false))
                        .build());
        insert(psrEvent, insertExpression, null);
    }

    private String generateEventCodeEventTime(String eventCode, Date eventTime) {
        return eventCode + qualifier + String.valueOf(eventTime.getTime());
    }
}
