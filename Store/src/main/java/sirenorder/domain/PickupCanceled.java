package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class PickupCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String customerAddr;
    private String customerTel;
    private String customerId;
    private String deliveryId;
    private String pickupId;
    private String pickupStatus;

    public PickupCanceled(Pickup aggregate) {
        super(aggregate);
    }

    public PickupCanceled() {
        super();
    }
    // keep

}
