package ua.volcaniccupcake.onlinestore.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.volcaniccupcake.onlinestore.model.*;
import ua.volcaniccupcake.onlinestore.model.security.Authority;
import ua.volcaniccupcake.onlinestore.model.security.Role;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.repository.*;
import ua.volcaniccupcake.onlinestore.repository.security.AuthorityRepository;
import ua.volcaniccupcake.onlinestore.repository.security.RoleRepository;
import ua.volcaniccupcake.onlinestore.repository.security.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();




    @Override
    public void run(ApplicationArguments args) throws Exception {
        initialize();
    }


    private void initialize() {
        Authority createProduct = authorityRepository.save(Authority.builder()
                .permission("product.create")
                .build());
        Authority updateProduct = authorityRepository.save(Authority.builder()
                .permission("product.update")
                .build());
        Authority deleteProduct = authorityRepository.save(Authority.builder()
                .permission("product.delete")
                .build());
        Authority readProduct = authorityRepository.save(Authority.builder()
                .permission("product.read")
                .build());

        Role userRole = roleRepository.save(Role.builder()
                .name("USER")
                .authority(readProduct)
                .build());
        Role adminRole = roleRepository.save(Role.builder()
                .name("ADMIN")
                .authority(createProduct)
                .authority(updateProduct)
                .authority(deleteProduct)
                .authority(readProduct)
                .build());

        User user = userRepository.save(User.builder()
                .username("user")
                .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-user"))
                .role(userRole)
                .build());
        User admin = userRepository.save(User.builder()
                .username("admin")
                .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-admin"))
                .role(adminRole)
                .build());

        Country ukraine = countryRepository.save(Country.builder()
                .name("Ukraine")
                .build());
        Country spain = countryRepository.save(Country.builder()
                .name("Spain")
                .build());
        Country unitedStates = countryRepository.save(Country.builder()
                .name("United States")
                .build());
        Country australia = countryRepository.save(Country.builder()
                .name("Australia")
                .build());

        Product monitor = productRepository.save(Product.builder()
                .name("monitor")
                .country(australia)
                .build());
        Product mouse = productRepository.save(Product.builder()
                .name("mouse")
                .country(ukraine)
                .build());
        Product microphone = productRepository.save(Product.builder()
                .name("microphone")
                .country(spain)
                .build());

        Item twoMonitors = itemRepository.save(Item.builder()
                .number(2)
                .product(monitor)
                .build());
        Item oneMonitor = itemRepository.save(Item.builder()
                .number(1)
                .product(monitor)
                .build());
        Item fiveMice = itemRepository.save(Item.builder()
                .number(5)
                .product(mouse)
                .build());

        Customer customerBob = customerRepository.save(Customer.builder()
                .user(user)
                .name("Bob")
                .email("bob@gmail.com")
                .phoneNumber("+380673332211")
                .build());

        Order order1 = orderRepository.save(Order.builder()
                .customer(customerBob)
                .item(twoMonitors)
                .item(fiveMice)
                .build());
        Order order2 = orderRepository.save(Order.builder()
                .customer(customerBob)
                .item(fiveMice)
                .build());
    }
}
