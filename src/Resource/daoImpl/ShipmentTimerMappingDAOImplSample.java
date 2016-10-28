package Resource.daoImpl;

import Resource.model.ShipmentTimerMapping;
import Resource.dao.ShipmentTimerMappingDAO;
import com.amazon.coral.google.common.collect.ImmutableMap;
import com.amazon.psrDao.dao.ShipmentTimerMappingDAO;
import com.amazon.psrDao.model.ShipmentTimerMapping;
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
public class ShipmentTimerMappingDAOImplSample extends GenericDAOImpl<ShipmentTimerMapping> implements ShipmentTimerMappingDAO {
    private static final String qualifier = "_";

    @Override
    public ShipmentTimerMapping getShipmentTimerMapping(String timerId) {
        if (StringUtils.isBlank(timerId)) {
            throw new IllegalArgumentException("Invalid arguments, Recieved timerId: " + timerId);
        }

        ShipmentTimerMapping shipmentTimerMapping = load(timerId);
        return shipmentTimerMapping;

        ShipmentTimerMapping hashKeyInstance = new ShipmentTimerMapping();
        hashKeyInstance.setTimerId(timerId);
        DynamoDBQueryExpression<ShipmentTimerMapping> queryExpression =
                new DynamoDBQueryExpression<ShipmentTimerMapping>().withHashKeyValues(hashKeyInstance);

        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("TIMER_ID", "#timerId");  // if TIMER_ID is a reserved key word in DynamoDB
        attributeNames.put("STATUS", "#status");     // if STATUS is a reserved key word in DynamoDB
        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":sche", new AttributeValue("SCHEDULED"));
        attributeValues.put(":proc", new AttributeValue("PROCESSED"));
        attributeValues.put(":timer_id", new AttributeValue("111111"));
        DynamoDBQueryExpression<ShipmentTimerMapping> queryExpression1 =
                new DynamoDBQueryExpression<ShipmentTimerMapping>()
                        .withExpressionAttributeNames(attributeNames)
                        .withExpressionAttributeValues(attributeValues)
                        .withKeyConditionExpression("#timerId = :timer_id")
                        // Adds a new condition to the the query filter.
                        .withQueryFilterEntry()
                        // Evaluates the query results and returns only the desired values out of the queried result.
                        /*.withExpressionAttributeValues(new ImmutableMap.Builder<String, AttributeValue>()
                                .put(":status", new AttributeValue(ShipmentTimerMappingStatus.SCHEDULED.toString()))
                                .build()).withExpressionAttributeValues(new ImmutableMap.Builder<String, AttributeValue>()
                        .put(":status", new AttributeValue(ShipmentTimerMappingStatus.SCHEDULED.toString()))
                        .build())
                        .withFilterExpression(ShipmentTimerMapping.STATUS + " = :status")*/
                        .withFilterExpression("#status IN (:sche, :proc)");
        List<ShipmentTimerMapping> result = query(queryExpression);

        if (result.isEmpty()) return null;
        return result.get(0);
    }

    @Override
    public ShipmentTimerMapping getShipmentTimerMapping(String shipmentId, String eventCode) {
        if (StringUtils.isBlank(shipmentId) || StringUtils.isBlank(eventCode)) {
            throw new IllegalArgumentException("Invalid arguments, Recieved shipmentId: " + shipmentId
                    + ", eventCode: " + eventCode);
        }

        Condition condition = new Condition();
        List<AttributeValue> attributes = new ArrayList<AttributeValue>();
        attributes.add(new AttributeValue(generateShipmentIdEventCode(shipmentId, eventCode)));
        condition.setAttributeValueList(attributes);
        condition.setComparisonOperator(ComparisonOperator.EQ);
        DynamoDBQueryExpression<ShipmentTimerMapping> queryExpression =
                new DynamoDBQueryExpression<ShipmentTimerMapping>()
                .withQueryFilterEntry(ShipmentTimerMapping.SHIPMENT_ID_EVENT_CODE, condition);

        ShipmentTimerMapping shipmentTimerMapping = new ShipmentTimerMapping();
        shipmentTimerMapping.setShipmentId(shipmentId);
        shipmentTimerMapping.setShipmentIdEventCode(generateShipmentIdEventCode(shipmentId, eventCode));

        DynamoDBQueryExpression<ShipmentTimerMapping> queryExpression =
                new DynamoDBQueryExpression<ShipmentTimerMapping>()
                        .withIndexName(ShipmentTimerMapping.GSI_SHIPMENT_ID_EVENT_CODE)
                        .withHashKeyValues(shipmentTimerMapping);
        List<ShipmentTimerMapping> result = query(queryExpression);
        if (result.isEmpty()) return null;
        return result.get(0);
    }

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
                                new AttributeValue(ShipmentTimerMapping.SHIPMENT_ID)).withExists(true))
                .put(ShipmentTimerMapping.SHIPMENT_ID_EVENT_CODE,
                        new ExpectedAttributeValue(
                                new AttributeValue(shipmentTimerMapping.getShipmentIdEventCode())).withExists(true))
                .build());
        update(shipmentTimerMapping, updateExpression,
                new DynamoDBMapperConfig(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
    }

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

    private String generateShipmentIdEventCode(String shipmentId, String eventCode) {
        return shipmentId + qualifier + eventCode;
    }
}
