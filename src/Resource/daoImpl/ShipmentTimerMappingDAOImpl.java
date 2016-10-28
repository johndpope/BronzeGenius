package Resource.daoImpl;

import Resource.model.ShipmentTimerMapping;
import Resource.dao.ShipmentTimerMappingDAO;
import com.amazon.coral.google.common.collect.ImmutableMap;
import com.amazon.psrDao.model.ShipmentTimerMapping;
import com.amazon.psrDao.model.exceptions.DataAccessException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author xuch
 */
public class ShipmentTimerMappingDAOImpl extends GenericDAOImpl<ShipmentTimerMapping> implements ShipmentTimerMappingDAO {
    private static final String qualifier = "_";

    /**
     * Check if the timer status is SCHEDULED or PROCESSED when a timer event comes around from the STS.
     * The read should be strongly consistent read.
     * DynamoDBMapper.load() use strongly consistent read.
     */
    @Override
    public ShipmentTimerMapping getShipmentTimerMapping(String timerId) {
        if (StringUtils.isBlank(timerId)) {
            throw new IllegalArgumentException("Invalid arguments, Recieved timerId: " + timerId);
        }

        ShipmentTimerMapping shipmentTimerMapping = load(timerId);
        return shipmentTimerMapping;
    }

    /**
     * Check if a timer exists or not when an event comes around with the event's shipmentId and eventCode
     * The read should be strongly consistent read.
     * However query on a GSI cannot work on strongly consistent read.
     */
    @Override
    public ShipmentTimerMapping getShipmentTimerMapping(String shipmentId, String eventCode) {
        if (StringUtils.isBlank(shipmentId) || StringUtils.isBlank(eventCode)) {
            throw new IllegalArgumentException("Invalid arguments, Recieved shipmentId: " + shipmentId
                    + ", eventCode: " + eventCode);
        }

        ShipmentTimerMapping shipmentTimerMapping = new ShipmentTimerMapping();
        shipmentTimerMapping.setShipmentId(shipmentId);
        shipmentTimerMapping.setShipmentIdEventCode(generateShipmentIdEventCode(shipmentId, eventCode));

        DynamoDBQueryExpression<ShipmentTimerMapping> queryExpression =
                new DynamoDBQueryExpression<ShipmentTimerMapping>()
                        .withIndexName(ShipmentTimerMapping.GSI_SHIPMENT_ID_EVENT_CODE)
                        .withHashKeyValues(shipmentTimerMapping)
                        .withConsistentRead(false);
        List<ShipmentTimerMapping> result = query(queryExpression);

        if (result.isEmpty()) {
            return null;
        } else if (result.size() > 1) {
            throw new DataAccessException("There are more than one item with shipmentId: " + shipmentId
                    + ", eventCode: " + eventCode + ", when querying ShipmentTimerMapping dynamoDB table.");
        }
        return result.get(0);
    }

    /**
     * Generally it is only used to update the status from "SCHEDULED" to "PROCESSED"
     * when all events associated with current timer have been processed in dynamoDB table "PSREvent".
     */
    @Override
    public void updateShipmentTimerMapping(ShipmentTimerMapping shipmentTimerMapping) {
        if (shipmentTimerMapping == null) {
            throw new IllegalArgumentException("Null ShipmentTimerMapping cannot be updated");
        }

        if (StringUtils.isBlank(shipmentTimerMapping.getTimerId())
                || StringUtils.isBlank(shipmentTimerMapping.getShipmentId())
                || StringUtils.isBlank(shipmentTimerMapping.getShipmentIdEventCode())) {
            throw new IllegalArgumentException(
                    "Invalid attributes, timerId:"
                            + shipmentTimerMapping.getTimerId() + ", shipmentId: "
                            + shipmentTimerMapping.getShipmentId() + ", shipmentIdEventCode: "
                            + shipmentTimerMapping.getShipmentIdEventCode());
        }

        DynamoDBSaveExpression updateExpression = new DynamoDBSaveExpression();
        updateExpression.withExpected(new ImmutableMap.Builder<String, ExpectedAttributeValue>()
                .put(ShipmentTimerMapping.TIMER_ID,
                        new ExpectedAttributeValue().withValue(
                                new AttributeValue(shipmentTimerMapping.getTimerId())).withExists(true))
                .put(ShipmentTimerMapping.SHIPMENT_ID,
                        new ExpectedAttributeValue().withValue(
                                new AttributeValue(shipmentTimerMapping.getShipmentId())).withExists(true))
                .put(ShipmentTimerMapping.SHIPMENT_ID_EVENT_CODE,
                        new ExpectedAttributeValue(
                                new AttributeValue(shipmentTimerMapping.getShipmentIdEventCode())).withExists(true))
                .build());
        update(shipmentTimerMapping, updateExpression,
                new DynamoDBMapperConfig(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
    }

    /**
     * @param shipmentTimerMapping
     * Generally it is only used to insert a new timer when an event comes around
     * under the circumstance that there is no timer for this event existing
     * or already a timer which is expiring.
     */
    @Override
    public void insertShipmentTimerMapping(ShipmentTimerMapping shipmentTimerMapping) {
        if (shipmentTimerMapping == null) {
            throw new IllegalArgumentException("Null ShipmentTimerMapping cannot be inserted");
        }

        if (StringUtils.isBlank(shipmentTimerMapping.getTimerId())
                || StringUtils.isBlank(shipmentTimerMapping.getShipmentId())
                || StringUtils.isBlank(shipmentTimerMapping.getShipmentIdEventCode())) {
            throw new IllegalArgumentException(
                    "Cannot insert containerInfo. Primary key attribute values are null, timerId:"
                            + shipmentTimerMapping.getTimerId() + ", shipmentId: "
                            + shipmentTimerMapping.getShipmentId() + ", shipmentIdEventCode: "
                            + shipmentTimerMapping.getShipmentIdEventCode());
        }
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression.setExpected(new ImmutableMap.Builder<String, ExpectedAttributeValue>()
                .put(ShipmentTimerMapping.TIMER_ID, new ExpectedAttributeValue(false)).build());

        insert(shipmentTimerMapping, saveExpression, null);
    }

    @Override
    public Class<ShipmentTimerMapping> getPersistantClassName() {
        return ShipmentTimerMapping.class;
    }

    public String generateShipmentIdEventCode(String shipmentId, String eventCode) {
        return shipmentId + qualifier + eventCode;
    }
}
