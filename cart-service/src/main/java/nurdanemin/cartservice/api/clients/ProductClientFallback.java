package nurdanemin.cartservice.api.clients;

import lombok.extern.slf4j.Slf4j;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.apache.kafka.clients.ClientResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class ProductClientFallback  implements ProductClient{
    @Override
    public ProductClientResponse getProductPrice(UUID productId) {
        log.info("INVENTORY_SERVICE_IS_DOWN");
        throw new BusinessException(("INVENTORY_SERVICE_IS_NOT_AVAILABLE_NOW"));
    }
}
