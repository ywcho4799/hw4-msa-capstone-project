package sirenorder.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sirenorder.config.kafka.KafkaProcessor;
import sirenorder.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PickupRepository pickupRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_RequestOrder(
        @Payload PaymentApproved paymentApproved
    ) {
        if (!paymentApproved.validate()) return;
        PaymentApproved event = paymentApproved;
        System.out.println(
            "\n\n##### listener RequestOrder : " +
            paymentApproved.toJson() +
            "\n\n"
        );

        // Sample Logic //
        Pickup.requestOrder(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_CancelPickup(
        @Payload PaymentCanceled paymentCanceled
    ) {
        if (!paymentCanceled.validate()) return;
        PaymentCanceled event = paymentCanceled;
        System.out.println(
            "\n\n##### listener CancelPickup : " +
            paymentCanceled.toJson() +
            "\n\n"
        );

        // Sample Logic //
        Pickup.cancelPickup(event);
    }
    // keep

}
