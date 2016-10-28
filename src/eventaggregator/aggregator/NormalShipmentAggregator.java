package eventaggregator.aggregator;

import com.amazon.psr.model.PSREvent;
import com.amazon.psrservice.model.PSRShipmentEvent;
import org.springframework.stereotype.Component;

/**
 * Created by xuch on 9/1/16.
 */
@Component
public class NormalShipmentAggregator extends AbstractShipmentAggregator<PSREvent, PSRShipmentEvent> {

    @Override
    public String getPayload() {
        return null;
    }

//    @Override
//    public ShipmentOperation.ShipmentOperationTarget getShipmentOperationTarget()
//    {
//        return ShipmentOperation.ShipmentOperationTarget.Normal;
//    }
}
