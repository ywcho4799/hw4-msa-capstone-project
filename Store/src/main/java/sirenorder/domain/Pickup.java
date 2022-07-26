package sirenorder.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import sirenorder.StoreApplication;
import sirenorder.domain.PickupCanceled;
import sirenorder.domain.PickupStarted;

@Entity
@Table(name = "Pickup_table")
@Data
public class Pickup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String pickupStatus;

    @PostPersist
    public void onPostPersist() {
        if(this.getPickupStatus() == "픽업 준비 완료"){
            PickupStarted pickupStarted = new PickupStarted(this);
            pickupStarted.publishAfterCommit();
        }
    }

    public static PickupRepository repository() {
        PickupRepository pickupRepository = StoreApplication.applicationContext.getBean(
            PickupRepository.class
        );
        return pickupRepository;
    }

    public static void requestOrder(PaymentApproved paymentApproved) {
        Pickup pickup = new Pickup();
        pickup.setOrderId(paymentApproved.getOrderId());
        repository().save(pickup);
    }

    public static void cancelPickup(PaymentCanceled paymentCanceled) {
        Pickup pickup = repository().findByOrderId(paymentCanceled.getOrderId());
        pickup.setPickupStatus("픽업 취소");

        PickupCanceled pickupCanceled = new PickupCanceled(pickup);
        pickupCanceled.publishAfterCommit();
    }
}
