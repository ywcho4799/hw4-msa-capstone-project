package sirenorder.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderDetails_table")
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String orderStatus;
    private String payStatus;
    private String menu;
    private Long paymentId;
    private String pickupStatus;

    public String getOrderStatus(){
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }
    
    public String getPayStatus(){
        return payStatus;
    }
    public void setPayStatus(String payStatus){
        this.payStatus = payStatus;
    }
    
    public String getPickupStatus(){
        return pickupStatus;
    }
    public void setPickupStatus(String pickupStatus){
        this.pickupStatus = pickupStatus;
    }
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getMenu(){
        return menu;
    }
    public void setMenu(String menu){
        this.menu = menu;
    }
    
}
