package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.infra.AbstractEvent;

@Data
public class OrderCancled extends AbstractEvent {

    private Long id;
    private String menu;
    private String customerId;
    private String customerName;
    private String customerTel;
    private Double price;
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
}
