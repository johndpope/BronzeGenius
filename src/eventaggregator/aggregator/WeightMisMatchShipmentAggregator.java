package eventaggregator.aggregator;

import com.amazon.psr.model.PSREvent;
import com.amazon.psrservice.model.PSRShipmentEvent;
import org.springframework.stereotype.Component;

/**
 * Created by xuch on 8/30/16.
 */
@Component
public class WeightMisMatchShipmentAggregator extends AbstractShipmentAggregator<PSREvent, PSRShipmentEvent> {
    @Override
    public String getPayload() {
        return null;
    }

//    @Override
//    public ShipmentOperation.ShipmentOperationTarget getShipmentOperationTarget() {
//        return ShipmentOperation.ShipmentOperationTarget.WeightMisMatch;
//    }
}
