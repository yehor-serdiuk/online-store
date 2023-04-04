package ua.volcaniccupcake.onlinestore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AppService {
    private final AppProperties appProperties;
    private final SneakersRepository sneakersRepository;
    private final CountryRepository countryRepository;
}
