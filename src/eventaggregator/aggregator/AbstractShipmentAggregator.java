package eventaggregator.aggregator;

import com.amazon.psr.model.PSREvent;
import com.amazon.psrservice.model.PSRShipmentEvent;
import com.amazon.psrservice.sts.CreateTimerInputFactory;
import com.amazon.psrservice.sts.SimpleTimerServiceNotifier;
import com.amazon.simpletimerservice.CreateTimerInput;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xuch on 8/30/16.
 */
public abstract class AbstractShipmentAggregator<T extends PSREvent, S extends PSRShipmentEvent> implements Aggregator<T, S> {
    @Autowired
    private CreateTimerInputFactory createTimerInputFactory;

    @Autowired
    private SimpleTimerServiceNotifier simpleTimerServiceNotifier;

    public abstract String getPayload();

    //public abstract ShipmentOperation.ShipmentOperationTarget getShipmentOperationTarget();

    @Override
    public PSRShipmentEvent execute(PSREvent event) {
        CreateTimerInput request = createTimerInputFactory.getInstance(event.getEventCode(), getPayload());
        simpleTimerServiceNotifier.notifySimpleTimerService(request);

        // TODO: dynamoDB action here

        return null;
    }
}
