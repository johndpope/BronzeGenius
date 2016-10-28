package eventaggregator.sts;

import com.amazon.metrics.declarative.WithMetrics;
import com.amazon.metrics.declarative.servicemetrics.Availability;
import com.amazon.metrics.declarative.servicemetrics.Latency;
import com.amazon.metrics.declarative.servicemetrics.ServiceMetric;
import com.amazon.metrics.declarative.servicemetrics.Timeout;
import com.amazon.psr.model.DependencyException;
import com.amazon.psr.model.InvalidInputException;
import com.amazon.simpletimerservice.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create timer and notify STS
 */
@WithMetrics
@Slf4j
@Component
public class SimpleTimerServiceNotifier
{
    @Autowired
    private SimpleTimerServiceClient simpleTimerServiceClient;

    @ServiceMetric(serviceName = "SimpleTimerService", operation = "CreateTimer")
    @Availability @Latency @Timeout
    public void notifySimpleTimerService(final CreateTimerInput request) {
        log.info("Inject a message to SimpleTimerService for clientId: {}, timerId: {}.",
                request.getClientId(), request.getClientTimerId());
        try {
            simpleTimerServiceClient.newCreateTimerCall().call(request);
        } catch (DuplicateTimerException e) {
            throw new InvalidInputException("Duplicate Timer with timer id ["
                    + request.getClientTimerId()
                    + "] conveyed to SimperTimerService", e);
        } catch (InvalidRequestException e) {
            throw new InvalidInputException("Invalid arguments containing client id ["
                    + request.getClientId() + "], timer id ["
                    + request.getClientTimerId() + "] of the timer conveyed to SimpleTimerService", e);
        } catch (ApplicationLogicException e) {
            throw new DependencyException(
                    "Exception occurred when conveying CreateTimer request to SimpleTimerService with client id ["
                     + request.getClientId() + "], timer id [" + request.getClientTimerId() + "]", e);
        }

        log.info("Timer was successfully created into SimpleTimerService, with client id: {}, timer id: {}.",
                request.getClientId(), request.getClientTimerId());
    }
}
