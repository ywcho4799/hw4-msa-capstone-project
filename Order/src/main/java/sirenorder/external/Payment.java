package sirenorder.external;

import java.util.Date;
import lombok.Data;

@Data
public class Payment {

    private Long id;
    private Long orderId;
    private Double price;
    private String payDate;
    private String cancelDate;
    // keep

}
