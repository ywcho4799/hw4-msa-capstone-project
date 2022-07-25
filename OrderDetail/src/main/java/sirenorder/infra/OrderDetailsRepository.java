package sirenorder.infra;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sirenorder.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "orderDetails",
    path = "orderDetails"
)
public interface OrderDetailsRepository
    extends PagingAndSortingRepository<OrderDetails, Long> {
    List<OrderDetails> findByOrderId(Long orderId);
    List<OrderDetails> findByOrderId(Long orderId);
    List<OrderDetails> findByOrderId(Long orderId);
    List<OrderDetails> findByOrderId(Long orderId);
    List<OrderDetails> findByOrderId(Long orderId);
    // keep

}
