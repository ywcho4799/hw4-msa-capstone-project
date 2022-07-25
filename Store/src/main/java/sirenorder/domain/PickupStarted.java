package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class PickupStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String customerAddr;
    private String customerTel;
    private String customerId;
    private String deliveryId;
    private String pickupId;
    private String pickupStatus;

    public PickupStarted(Pickup aggregate) {
        super(aggregate);
    }

    public PickupStarted() {
        super();
    }
    // keep

}
