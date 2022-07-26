package sirenorder.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import sirenorder.OrderApplication;
import sirenorder.domain.OrderCancled;
import sirenorder.domain.Ordered;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String menu;

    private Double price;

    private String customerId;

    private String customerName;

    private String customerTel;

    @PostPersist
    public void onPostPersist() {
        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        OrderCancled orderCancled = new OrderCancled(this);
        orderCancled.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
