package ua.volcaniccupcake.onlinestore.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public void run(ApplicationArguments args) {
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
        Authority customerCreateProduct = authorityRepository.save(Authority.builder()
                .permission("customer.product.create")
                .build());
        Authority customerUpdateProduct = authorityRepository.save(Authority.builder()
                .permission("customer.product.update")
                .build());
        Authority customerDeleteProduct = authorityRepository.save(Authority.builder()
                .permission("customer.product.delete")
                .build());
        Authority customerReadProduct = authorityRepository.save(Authority.builder()
                .permission("customer.product.read")
                .build());
        Authority createOrder = authorityRepository.save(Authority.builder()
                .permission("order.create")
                .build());
        Authority updateOrder = authorityRepository.save(Authority.builder()
                .permission("order.update")
                .build());
        Authority deleteOrder = authorityRepository.save(Authority.builder()
                .permission("order.delete")
                .build());
        Authority readOrder = authorityRepository.save(Authority.builder()
                .permission("order.read")
                .build());
        Authority customerCreateOrder = authorityRepository.save(Authority.builder()
                .permission("customer.order.create")
                .build());
        Authority customerUpdateOrder = authorityRepository.save(Authority.builder()
                .permission("customer.order.update")
                .build());
        Authority customerDeleteOrder = authorityRepository.save(Authority.builder()
                .permission("customer.order.delete")
                .build());
        Authority customerReadOrder = authorityRepository.save(Authority.builder()
                .permission("customer.order.read")
                .build());

        Role userRole = roleRepository.save(Role.builder()
                .name("USER")
                .authority(customerReadProduct)
                .authority(customerCreateOrder)
                .authority(customerDeleteOrder)
                .authority(customerReadOrder)
                .authority(customerUpdateOrder)
                .build());
        Role adminRole = roleRepository.save(Role.builder()
                .name("ADMIN")
                .authority(createProduct)
                .authority(updateProduct)
                .authority(deleteProduct)
                .authority(readProduct)
                .authority(createOrder)
                .authority(updateOrder)
                .authority(deleteOrder)
                .authority(readOrder)
                .build());
        Role restrictedRole = roleRepository.save(Role.builder()
                .name("RESTRICTED")
                .build());
        Customer customerUser = customerRepository.save(Customer.builder()
                .name("Bob")
                .email("bob@gmail.com")
                .phoneNumber("+380673332211")
                .build());
        Customer customerRestricted = customerRepository.save(Customer.builder()
                .name("Andrew")
                .email("andrew@gmail.com")
                .phoneNumber("+380673333333")
                .build());
        User user = userRepository.save(User.builder()
                .username("user")
                .customer(customerUser)
                .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-user"))
                .role(userRole)
                .build());
        User restricted = userRepository.save(User.builder()
                .username("restricted")
                .customer(customerRestricted)
                .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-restricted"))
                .role(restrictedRole)
                .build());
        log.debug("Loaded user");
        log.debug("     username: " + user.getUsername());
        log.debug("     password: " + user.getPassword());
        User admin = userRepository.save(User.builder()
                .username("admin")
                .password("{bcrypt}" + bCryptPasswordEncoder.encode("password-admin"))
                .role(adminRole)
                .build());
        log.debug("Loaded user");
        log.debug("     username: " + admin.getUsername());
        log.debug("     password: " + admin.getPassword());
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


        Order order1 = orderRepository.save(Order.builder()
                .customer(customerUser)
                .build());
        Order order2 = orderRepository.save(Order.builder()
                .customer(customerUser)
                .build());

        Item twoMonitors = itemRepository.save(Item.builder()
                .number(2)
                .order(order1)
                .product(monitor)
                .build());
        Item oneMonitor = itemRepository.save(Item.builder()
                .number(1)
                .order(order1)
                .product(monitor)
                .build());
        Item fiveMice = itemRepository.save(Item.builder()
                .number(5)
                .order(order2)
                .product(mouse)
                .build());
    }
}
