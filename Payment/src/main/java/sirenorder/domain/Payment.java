package sirenorder.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import sirenorder.PaymentApplication;
import sirenorder.domain.PaymentApproved;
import sirenorder.domain.PaymentCanceled;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Double price;

    private String payDate;

    private String cancelDate;

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }
    public Long getOrderId(){
        return orderId;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }
    public void setPayDate(String payDate){
        this.payDate = payDate;
    }
    public String getPayDate(){
        return payDate;
    }
    public void setCancelDate(String cancelDate){
        this.cancelDate = cancelDate;
    }
    public String getCancelDate(){
        return cancelDate;
    }

    @PostUpdate
    public void onPostUpdate() {
        PaymentApproved paymentApproved = new PaymentApproved(this);
        paymentApproved.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void cancelPayment(OrderCancled orderCancled) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
    public static void requestPayment(Ordered ordered) {
        /** Example 1:  new item */

        Payment payment = new Payment();
        payment.setOrderId(ordered.getId());
        payment.setPrice(ordered.getPrice());
        repository().save(payment);

        /** Example 2:  finding and process
        
        repository().findById(ordered.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
}
