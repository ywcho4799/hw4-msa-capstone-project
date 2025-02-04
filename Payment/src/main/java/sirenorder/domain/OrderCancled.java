package sirenorder.domain;

import java.util.Date;
import java.util.Map;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class OrderCancled extends AbstractEvent {

    private Long id;
    private String menu;
    private String customerId;
    private String customerName;
    private String customerTel;
    private Double price;
    // keep

}
