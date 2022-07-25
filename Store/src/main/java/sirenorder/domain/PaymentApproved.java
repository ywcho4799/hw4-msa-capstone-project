package sirenorder.domain;

import java.util.Date;
import java.util.Map;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Double price;
    private String payDate;
    // keep

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long orderId() {
        return id;
    }

    public void orderId(Long orderId) {
        this.orderId = orderId;
    }
}
