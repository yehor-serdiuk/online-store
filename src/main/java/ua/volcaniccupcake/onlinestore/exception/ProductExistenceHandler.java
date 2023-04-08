package ua.volcaniccupcake.onlinestore.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

@Component
@AllArgsConstructor
public class ProductExistenceHandler {

    private ProductRepository productRepository;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void listen() {
        if ( productRepository.count() == 0 )
            throw new NoProductException();
    }

}
