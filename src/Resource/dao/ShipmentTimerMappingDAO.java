package Resource.dao;

import Resource.model.ShipmentTimerMapping;
import com.amazon.psrDao.model.ShipmentTimerMapping;
import com.amazon.psrDao.model.exceptions.DataAccessException;
import com.amazon.psrDao.model.exceptions.DataDoesNotExistException;
import com.amazon.psrDao.model.exceptions.PrimaryKeyViolationException;
import com.amazon.psrDao.model.exceptions.StaleVersionException;

/**
 * @author xuch
 */
public interface ShipmentTimerMappingDAO {

    /**
     * Gets the shipment associated to the argument timer. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     *
     * @param timerId
     *            timer id
     * @return {@link ShipmentTimerMapping}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     */
    ShipmentTimerMapping getShipmentTimerMapping(String timerId);

    /**
     * One timer only be associated with one shipmentId_eventCode.
     * And One shipmentId_eventCode only be associated with one timer.
     * Gets the timer associated to the shipmentId_eventCode. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     *
     * @param shipmentId
     * @param eventCode
     * @return {@link ShipmentTimerMapping}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     */
    ShipmentTimerMapping getShipmentTimerMapping(String shipmentId, String eventCode);

    /**
     * Update shipment timer mapping. It throws DataAccessException if there are
     * underlying service exceptions. IllegalArgumentException will be thrown if
     * any of the input param is null.
     * PrimaryKeyViolationException.
     *
     * @param {@link ShipmentTimerMapping}
     *            the shipment timer mapping
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     * @throws DataDoesNotExistException
     * @throws StaleVersionException if DynamoDBMapper.save() throws ConditionalCheckFailedException
     */
    void updateShipmentTimerMapping(ShipmentTimerMapping shipmentTimerMapping);

    /**
     * Insert shipment timer mapping which represents a new timer associated to some certain container status events. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     * PrimaryKeyViolationException.
     *
     * @param {@link ShipmentTimerMapping}
     *            the shipment timer mapping
     * @throws IllegalArgumentException
     *             If shipmentTimerMapping is null or primary key attribute values are null
     * @throws DataAccessException
     *             the data access exception
     * @throws PrimaryKeyViolationException
     *             the primary key violation exception
     * @throws StaleVersionException if DynamoDBMapper.save() throws ConditionalCheckFailedException
     */
    void insertShipmentTimerMapping(ShipmentTimerMapping shipmentTimerMapping);
}
