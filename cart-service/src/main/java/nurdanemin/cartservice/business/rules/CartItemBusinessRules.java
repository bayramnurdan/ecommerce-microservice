package nurdanemin.cartservice.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.cartservice.api.clients.ProductClient;
import nurdanemin.cartservice.repository.CartItemRepository;
import nurdanemin.commonpackage.utils.constants.Messages;
import nurdanemin.commonpackage.utils.dto.ProductClientResponse;
import nurdanemin.commonpackage.utils.enums.ProductState;
import nurdanemin.commonpackage.utils.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartItemBusinessRules {
    private final CartItemRepository repository;
    private final ProductClient productClient;


    public ProductClientResponse checkIfProductAvailable(UUID productId){
        ProductClientResponse info = productClient.getProductInfo(productId);

        if (!info.isSuccess()){
            throw new BusinessException(Messages.Product.NOT_EXIST);
        } else if (info.isSuccess() && info.getProductState().equals(ProductState.NOTINSTOCK)){
            throw new BusinessException((Messages.Product.NOT_IN_STOCK));

        }
        return info;
    }
}