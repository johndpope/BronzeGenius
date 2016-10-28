package eventaggregator.manager;

import com.amazon.psr.model.PSREvent;
import com.amazon.psrservice.aggregator.Aggregator;
import com.amazon.psrservice.aggregator.AggregatorFactory;
import com.amazon.psrservice.sts.SimpleTimerServiceNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuch on 8/30/16.
 */
@Component
public class ShipmentAggregationManager implements Manager<PSREvent> {
    @Autowired
    private AggregatorFactory aggregatorFactory;

    @Autowired
    private SimpleTimerServiceNotifier simpleTimerServiceNotifier;

    @Override
    public void manage(PSREvent psrEvent) {
        // TODO
        // aggregator
        Aggregator aggregator = aggregatorFactory.getInstance(psrEvent.getEventCode());
        aggregator.execute(psrEvent);

        // STS
        //simpleTimerServiceNotifier.notifySimpleTimerService();


        // DAO: DynamoDB
    }
}
