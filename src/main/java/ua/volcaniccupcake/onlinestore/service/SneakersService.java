package ua.volcaniccupcake.onlinestore.service;

import ua.volcaniccupcake.onlinestore.model.Sneakers;

import java.util.Optional;

public interface SneakersService {

    Sneakers createSneakers(Sneakers sneakers);
    Optional<Sneakers> getSneakersById(long sneakersId);
    Iterable<Sneakers> getSneakers();
    void updateSneakers(long sneakersId, Sneakers sneakers);
    void deleteSneakersById(long sneakersId);
    void deleteSneakers();
}
