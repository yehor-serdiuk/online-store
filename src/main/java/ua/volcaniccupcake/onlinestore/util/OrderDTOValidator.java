package ua.volcaniccupcake.onlinestore.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.dto.ItemDTO;
import ua.volcaniccupcake.onlinestore.model.dto.OrderDTO;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.repository.OrderRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderDTOValidator {
    private final ProductRepository productRepository;

    public boolean isValid(OrderDTO orderDTO) {
        for (ItemDTO itemDTO : orderDTO.getItems()) {
            if (!productRepository.existsById(itemDTO.getProduct().getId())) {
                return false;
            }
        }

        return true;
    }
}
