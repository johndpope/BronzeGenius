package eventaggregator.sts;

import amazon.AmazonUUID.UUIDGenerator;
import com.amazon.simpletimerservice.CreateTimerInput;
import com.amazon.transportation.spec.events.EventCodes;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Generate CreateTimerInput.
 * For different timer, there may be different client ids and timer durations.
 * CreateTimerInputFactory can get the config instance depends on deffirent requirements configured in brazil-config file.
 * This provides possibility for us to extend to multiple clients and schedule tasks.
 */
@Component
public class CreateTimerInputFactory
{
    @Resource(name="aggregationSimpleTimerServiceClientId")
    private String aggregationSimpleTimerServiceClientId;

    @Resource(name="aggregationDefaultTimerDurationSeconds")
    private Integer aggregationDefaultTimerDurationSeconds;

    @Resource(name="aggregationLowTimerDurationSeconds")
    private Integer aggregationLowTimerDurationSeconds;

    @Resource(name="aggregationHighTimerDurationSeconds")
    private Integer aggregationHighTimerDurationSeconds;

    @Autowired
    private Gson gson;

    public <T> CreateTimerInput getInstance(String eventCode, T payload) {
        String clientId;
        Integer timerDurationSeconds;
        switch (eventCode) {
            case EventCodes.PickupDone:
            case EventCodes.ArrivedAtFirstCarrierFacility:
            case EventCodes.PickupCancelled:
            case EventCodes.Lost:
            case EventCodes.Delivered:
            case EventCodes.Undeliverable:
            case EventCodes.Rejected:
            case EventCodes.OutForDelivery:
            case EventCodes.Delayed:
            case EventCodes.DeliveryAttempted:
            case EventCodes.Received:
                clientId = aggregationSimpleTimerServiceClientId;
                timerDurationSeconds = aggregationLowTimerDurationSeconds;
                break;
            case EventCodes.ScaleWeightChanged:
            case EventCodes.DimensionalWeightChanged:
            case EventCodes.ReadyForReturn:
                clientId = aggregationSimpleTimerServiceClientId;
                timerDurationSeconds = aggregationHighTimerDurationSeconds;
                break;
            default:
                clientId = aggregationSimpleTimerServiceClientId;
                timerDurationSeconds = aggregationDefaultTimerDurationSeconds;
                break;
        }

        return getInstance(clientId, getTimerId(), getRequestedTime(timerDurationSeconds), payload);
    }

    private <T> CreateTimerInput getInstance(String clientId, String timerId, Date requestedTime, T payload) {
        String payloadStr;
        if (payload.getClass().equals(String.class)) {
            payloadStr = (String) payload;
        }
        else {
            payloadStr = gson.toJson(payload, payload.getClass());
        }
        CreateTimerInput request = new CreateTimerInput();
        request.setClientId(clientId);
        request.setClientTimerId(timerId);
        request.setRequestedTime(requestedTime);
        request.setPayload(payloadStr);
        return request;
    }

    private Date getRequestedTime(Integer timerDurationSeconds) {
        return new DateTime().plusSeconds(timerDurationSeconds).toDate();
    }

    private String getTimerId() {
        return new UUIDGenerator().generate();
    }

}
