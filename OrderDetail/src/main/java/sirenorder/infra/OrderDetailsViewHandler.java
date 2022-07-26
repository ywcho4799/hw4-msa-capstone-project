package sirenorder.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sirenorder.config.kafka.KafkaProcessor;
import sirenorder.domain.*;

@Service
public class OrderDetailsViewHandler {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1(@Payload Ordered ordered) {
        try {
            if (!ordered.validate()) return;

            // view 객체 생성
            OrderDetails orderDetails = new OrderDetails();
            // view 객체에 이벤트의 Value 를 set 함
            orderDetails.setOrderId(ordered.getId());
            orderDetails.setMenu(ordered.getMenu());
            orderDetails.setOrderStatus(ordered.getOrderStatus);
            // view 레파지 토리에 save
            orderDetailsRepository.save(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_1(
        @Payload PaymentApproved paymentApproved
    ) {
        try {
            if (!paymentApproved.validate()) return;
            // view 객체 조회

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(
                Long.valueOf(paymentApproved.getOrderId())
            );
            for (OrderDetails orderDetails : orderDetailsList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderDetails.setPayStatus("결제 완료");
                orderDetails.setOrderStatus("제조 시작");
                // view 레파지 토리에 save
                orderDetailsRepository.save(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancled_then_UPDATE_2(
        @Payload OrderCancled orderCancled
    ) {
        try {
            if (!orderCancled.validate()) return;
            // view 객체 조회

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(
                orderCancled.getId()
            );
            for (OrderDetails orderDetails : orderDetailsList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderDetails.setOrderStatus("주문 취소");
                // view 레파지 토리에 save
                orderDetailsRepository.save(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCanceled_then_UPDATE_3(
        @Payload PaymentCanceled paymentCanceled
    ) {
        try {
            if (!paymentCanceled.validate()) return;
            // view 객체 조회

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(
                Long.valueOf(paymentCanceled.getOrderId())
            );
            for (OrderDetails orderDetails : orderDetailsList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderDetails.setPayStatus("결제 취소");
                orderDetails.setOrderStatus("주문 취소 완료");
                // view 레파지 토리에 save
                orderDetailsRepository.save(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPickupStarted_then_UPDATE_4(
        @Payload PickupStarted pickupStarted
    ) {
        try {
            if (!pickupStarted.validate()) return;
            // view 객체 조회

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(
                pickupStarted.getOrderId()
            );
            for (OrderDetails orderDetails : orderDetailsList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderDetails.setPickupStatus("픽업 준비 완료");
                // view 레파지 토리에 save
                orderDetailsRepository.save(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPickupCanceled_then_UPDATE_5(
        @Payload PickupCanceled pickupCanceled
    ) {
        try {
            if (!pickupCanceled.validate()) return;
            // view 객체 조회

            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(
                pickupCanceled.getOrderId()
            );
            for (OrderDetails orderDetails : orderDetailsList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderDetails.setPickupStatus("픽업 취소");
                // view 레파지 토리에 save
                orderDetailsRepository.save(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // keep

}
