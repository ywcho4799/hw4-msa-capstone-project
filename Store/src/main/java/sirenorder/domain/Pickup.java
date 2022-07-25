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

    private Long orderId;

    private String customerAddr;

    private String customerTel;

    private String customerId;

    private String pickupId;

    private String pickupStatus;

    @PostPersist
    public void onPostPersist() {
        PickupStarted pickupStarted = new PickupStarted(this);
        pickupStarted.publishAfterCommit();

        PickupCanceled pickupCanceled = new PickupCanceled(this);
        pickupCanceled.publishAfterCommit();
    }

    public static PickupRepository repository() {
        PickupRepository pickupRepository = StoreApplication.applicationContext.getBean(
            PickupRepository.class
        );
        return pickupRepository;
    }

    public static void requestOrder(PaymentApproved paymentApproved) {
        /** Example 1:  new item 
        Pickup pickup = new Pickup();
        repository().save(pickup);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentApproved.get???()).ifPresent(pickup->{
            
            pickup // do something
            repository().save(pickup);


         });
        */

    }

    public static void cancelPickup(PaymentCanceled paymentCanceled) {
        /** Example 1:  new item 
        Pickup pickup = new Pickup();
        repository().save(pickup);

        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(pickup->{
            
            pickup // do something
            repository().save(pickup);


         });
        */

    }
}
