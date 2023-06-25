package ua.volcaniccupcake.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;
import ua.volcaniccupcake.onlinestore.service.ProductService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }


    @Test
    void getAllProduct() throws Exception {
        mvc.perform(get("/product").with(httpBasic("user", "password-user")))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void getProductById() {
    }

    @Test
    @Disabled
    void createProduct() throws Exception {
        Product product = new Product();
        product.setName("monitor");
        product.setCountry(countryRepository.findByName("Ukraine").get());
        assert(productRepository.count() == 10);
        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
        assert(productRepository.count() == 11);
    }

    @Test
    @Disabled
    void updateCourse() {
    }

    @Test
    @Disabled
    void deleteProductById() {
    }

    @Test
    @Disabled
    void deleteProduct() {
    }
}