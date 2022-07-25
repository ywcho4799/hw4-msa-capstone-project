package sirenorder.domain;

import java.util.Date;
import lombok.Data;
import sirenorder.infra.AbstractEvent;

@Data
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Double price;
    private String payDate;
    private String cancelDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price
    }
   
    public String getPaydate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }
}
