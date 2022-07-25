package sirenorder.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sirenorder.domain.*;

@RepositoryRestResource(collectionResourceRel = "pickups", path = "pickups")
public interface PickupRepository
    extends PagingAndSortingRepository<Pickup, String> {}
