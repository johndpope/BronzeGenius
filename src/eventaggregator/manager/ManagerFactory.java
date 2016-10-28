package eventaggregator.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import org.hibernate.ObjectNotFoundException;

/**
 * Created by xuch on 9/1/16.
 */
@Component
public class ManagerFactory {
    @Autowired
    private ShipmentAggregationManager shipmentAggregationManager;

    @Autowired
    private ShipmentUpdateManager shipmentUpdateManager;

    /*private final Map<ShipmentOperation, Manager> warehouse = new HashMap<ShipmentOperation, Manager>() {
        {
            put(ShipmentOperation.Aggregation, shipmentAggregationManager);
            put(ShipmentOperation.Update, shipmentUpdateManager);
        }
    };

    public Manager getInstance(ShipmentOperation operation) {
        if (warehouse.containsKey(operation)) return warehouse.get(operation);
        throw new NotFoundException("Unknow Shipment Operation found at Factory");
    }*/
}
