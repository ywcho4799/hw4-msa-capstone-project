package sirenorder.domain;

import java.util.Date;
import lombok.Data;
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


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}
