package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Double price;
    private String payDate;
    private String cancelDate;

    public PaymentCanceled(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCanceled() {
        super();
    }
    // keep

}
