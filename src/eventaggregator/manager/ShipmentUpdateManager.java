package eventaggregator.manager;

import com.amazon.psrservice.model.PSRShipmentEvent;
import com.amazon.psrservice.update.handler.IUpdateHandler;
import com.amazon.psrservice.update.handler.shipment.ShipmentHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xuch on 8/30/16.
 */
@Component
public class ShipmentUpdateManager implements Manager<PSRShipmentEvent> {
    @Autowired
    private ShipmentHandlerFactory shipmentHandlerFactory;

    @Override
    public void manage(PSRShipmentEvent psrShipmentEvent) {
        IUpdateHandler handler = shipmentHandlerFactory.getInstance(psrShipmentEvent.toString());
        handler.handle(null);
    }
}
