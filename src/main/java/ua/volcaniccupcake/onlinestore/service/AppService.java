package ua.volcaniccupcake.onlinestore.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.SneakersRepository;
import ua.volcaniccupcake.onlinestore.config.AppProperties;

@Service
@Data
@RequiredArgsConstructor
public class AppService {
    private final AppProperties appProperties;
    private final SneakersRepository sneakersRepository;
    private final CountryRepository countryRepository;
}
