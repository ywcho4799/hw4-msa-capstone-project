package sirenorder.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import sirenorder.domain.*;

@Component
public class PickupHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Pickup>> {

    @Override
    public EntityModel<Pickup> process(EntityModel<Pickup> model) {
        return model;
    }
}
