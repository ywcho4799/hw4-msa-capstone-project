package sirenorder.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import sirenorder.PaymentApplication;
import sirenorder.domain.PaymentApproved;
import sirenorder.domain.PaymentCanceled;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        if(this.getCancelDate() == null){
            PaymentApproved paymentApproved = new PaymentApproved(this);
            paymentApproved.publishAfterCommit();
        }
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void cancelPayment(OrderCancled orderCancled) {
        Payment payment = repository().findByOrderId(orderCancled.getId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();        
		String dateToStr = dateFormat.format(date);

        payment.setCancelDate(dateToStr);
        repository().save(payment);

        PaymentCanceled paymentCanceled = new PaymentCanceled(payment);
        paymentCanceled.publishAfterCommit();
    }
    public static void requestPayment(Ordered ordered) {
        Payment payment = new Payment();
        payment.setOrderId(ordered.getId());
        payment.setPrice(ordered.getPrice());
        repository().save(payment);
    }
}
