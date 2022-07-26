package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class PaymentApproved extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Double price;
    private String payDate;

    public PaymentApproved(Payment aggregate) {
        super(aggregate);
    }

    public PaymentApproved() {
        super();
    }
    
    // keep

}
