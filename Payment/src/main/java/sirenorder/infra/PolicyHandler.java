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
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancled_CancelPayment(
        @Payload OrderCancled orderCancled
    ) {
        if (!orderCancled.validate()) return;
        OrderCancled event = orderCancled;
        System.out.println(
            "\n\n##### listener CancelPayment : " +
            orderCancled.toJson() +
            "\n\n"
        );

        // Sample Logic //
        Payment.cancelPayment(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_RequestPayment(@Payload Ordered ordered) {
        if (!ordered.validate()) return;
        Ordered event = ordered;
        System.out.println(
            "\n\n##### listener RequestPayment : " + ordered.toJson() + "\n\n"
        );

        // Sample Logic //
        Payment.requestPayment(event);
    }
    // keep

}
