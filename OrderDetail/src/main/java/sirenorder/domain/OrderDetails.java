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
    public void setOrderStatus(STring orderStatus){
        this.orderStatus = orderStatus
    }
    
    public String getPayStatus(){
        return payStatus;
    }
    public void setPayStatus(STring payStatus){
        this.payStatus = payStatus
    }
    
    public String getPickupStatus(){
        return pickupStatus;
    }
    public void setPickupStatus(STring pickupStatus){
        this.pickupStatus = pickupStatus
    }
    
}
