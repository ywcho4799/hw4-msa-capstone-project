package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.domain.*;
import sirenorder.infra.AbstractEvent;

@Data
public class Ordered extends AbstractEvent {

    private Long id;
    private String menu;
    private String customerId;
    private String customerName;
    private String customerTel;
    private Double price;

    public Ordered(Order aggregate) {
        super(aggregate);
    }

    public Ordered() {
        super();
    }
    // keep

}
