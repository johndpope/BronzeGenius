package eventaggregator.aggregator;

import com.amazon.transportation.spec.events.EventCodes;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuch on 9/1/16.
 */
@Component
public class AggregatorFactory {
    @Autowired
    private NormalShipmentAggregator normalShipmentAggregator;

    @Autowired
    private WeightMisMatchShipmentAggregator weightMisMatchShipmentAggregator;

    @Autowired
    private ReadyForReturnShipmentAggregator readyForReturnShipmentAggregator;

    private final Map<String, Aggregator> warehouse = new HashMap<String, Aggregator>() {
        {
            put(EventCodes.ScaleWeightChanged, weightMisMatchShipmentAggregator);
            put(EventCodes.DimensionalWeightChanged, weightMisMatchShipmentAggregator);
            put(EventCodes.ReadyForReturn, readyForReturnShipmentAggregator);
            //put(Void, Void);
        }
    };

    public Aggregator getInstance(String eventCode) {
        Validate.notNull(eventCode, "EventCode was found null");
        switch (eventCode) {
            case EventCodes.DimensionalWeightChanged:
            case EventCodes.ScaleWeightChanged:
                return weightMisMatchShipmentAggregator;
            case EventCodes.ReadyForReturn:
                return readyForReturnShipmentAggregator;
            default:
                return normalShipmentAggregator;
                //throw new NotFoundException("EventCode is not found");
        }
    }
}
