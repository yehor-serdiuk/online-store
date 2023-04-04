package ua.volcaniccupcake.onlinestore;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SneakerExistenceHandler {

    private SneakersRepository sneakersRepository;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void listen() {
        if ( sneakersRepository.count() == 0 )
            throw new NoSneakersException();
    }

}
