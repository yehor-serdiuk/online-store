package ua.volcaniccupcake.onlinestore;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SneakersServiceImpl implements SneakersService {
    private final SneakersRepository sneakersRepository;

    @Override
    public Sneakers createSneakers(Sneakers sneakers) {
        return sneakersRepository.save(sneakers);
    }

    @Override
    public Optional<Sneakers> getSneakersById(long sneakersId) {
        return sneakersRepository.findById(sneakersId);
    }

    @Override
    public Iterable<Sneakers> getSneakers() {
        return sneakersRepository.findAll();
    }

    @Override
    public void updateSneakers(long sneakersId, Sneakers sneakers) {
        sneakersRepository.findById(sneakersId).ifPresentOrElse(dbSneakers -> {
            dbSneakers.setName(sneakers.getName());

            sneakersRepository.save(dbSneakers);
        },
        () -> sneakersRepository.save(sneakers));
    }

    @Override
    public void deleteSneakersById(long sneakersId) {
        sneakersRepository.findById(sneakersId).orElseThrow(() -> new SneakersNotFoundException(
                String.format("No sneakers with id %s is available", sneakersId)));
        sneakersRepository.deleteById(sneakersId);
    }

    @Override
    public void deleteSneakers() {
        sneakersRepository.deleteAll();
    }
}
