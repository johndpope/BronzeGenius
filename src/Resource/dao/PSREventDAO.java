package Resource.dao;

import Resource.model.PSREvent;
import com.amazon.psrDao.model.PSREvent;
import com.amazon.psrDao.model.exceptions.DataAccessException;
import com.amazon.psrDao.model.exceptions.DataDoesNotExistException;
import com.amazon.psrDao.model.exceptions.PrimaryKeyViolationException;
import com.amazon.psrDao.model.exceptions.StaleVersionException;

import java.util.Date;
import java.util.List;

/**
 * @author xuch.
 */
public interface PSREventDAO {
    /**
     * Gets container status event like PickupDone, WMM and ReadyForReturn. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     *
     * @param trackingId
     *            tracking id of the container
     * @param eventCode
     *            eventCode of the container
     * @param eventTime
     *            eventTime of the container
     * @return {@link PSREvent}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     */
    PSREvent getPSREvent(String trackingId, String eventCode, Date eventTime);

    /**
     * Gets container status event like PickupDone, WMM and ReadyForReturn. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     *
     * @param trackingId
     *            tracking id of the container
     * @param eventCodeEventTime
     *            eventCode_eventTime of the container
     * @return {@link PSREvent}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     */
    PSREvent getPSREvent(String trackingId, String eventCodeEventTime);

    /**
     * Gets all container status events belonged to the argument timer. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     *
     * @param timerId
     *            timer id
     * @return {@link PSREvent}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     */
    List<PSREvent> getPSREvents(String timerId);


    /**
     * Update PSREvent. It throws DataAccessException if there are
     * underlying service exceptions. IllegalArgumentException will be thrown if
     * any of the input param is null.
     * PrimaryKeyViolationException.
     *
     * @param {@link PSREvent}
     * @throws IllegalArgumentException
     *             the illegal argument exception
     * @throws DataAccessException
     *             the data access exception
     * @throws DataDoesNotExistException
     * @throws StaleVersionException if DynamoDBMapper.save() throws ConditionalCheckFailedException
     */
    void updatePSREvent(PSREvent psrEvent);

    /**
     * Insert PSREvent which represents a container status event. It throws
     * DataAccessException if there are underlying service exceptions.
     * IllegalArgumentException will be thrown if any of the input param is null.
     * PrimaryKeyViolationException.
     *
     * @param {@link PSREvent}
     * @throws IllegalArgumentException
     *             If psrEvent is null or primary key attribute values are null
     * @throws DataAccessException
     *             the data access exception
     * @throws PrimaryKeyViolationException
     *             the primary key violation exception
     * @throws StaleVersionException if DynamoDBMapper.save() throws ConditionalCheckFailedException
     */
    void insertPSREvent(PSREvent psrEvent);

}
