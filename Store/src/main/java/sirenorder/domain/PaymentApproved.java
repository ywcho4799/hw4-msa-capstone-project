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

}
