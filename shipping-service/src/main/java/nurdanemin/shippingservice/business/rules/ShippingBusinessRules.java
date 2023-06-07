package nurdanemin.shippingservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import nurdanemin.shippingservice.entities.Shipping;
import nurdanemin.shippingservice.entities.ShippingStatus;
import nurdanemin.shippingservice.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShippingBusinessRules {
    private ShippingRepository repository;

    public void checkIfShippingSuitableForUpdate(UUID shippingId){
        Shipping shipping =repository.findById(shippingId).orElseThrow();
        if (!shipping.getStatus().equals(ShippingStatus.NOT_STARTED)){
            throw new BusinessException(Messages.Shipping.NOT_SUITABLE_FOR_UPDATE);
        }
    }
}