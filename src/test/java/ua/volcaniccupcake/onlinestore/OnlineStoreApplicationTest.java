package ua.volcaniccupcake.onlinestore;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OnlineStoreApplicationTest {
    @Autowired
    SneakersRepository sneakersRepository;

    Logger logger = LoggerFactory.getLogger(OnlineStoreApplicationTest.class);

    @Test
    void testSneakers() {
        sneakersRepository.findAll(Pageable.unpaged()).forEach((Sneakers sneakers) -> {
            logger.info(sneakers.getName());
        });
    }
}