package ua.volcaniccupcake.onlinestore.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.repository.SneakersRepository;

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
