package ua.volcaniccupcake.onlinestore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;
import ua.volcaniccupcake.onlinestore.service.ProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllProduct() throws Exception {
        mvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() {
    }

    @Test
    void createProduct() throws Exception {
        Product product = new Product();
        product.setName("monitor");
        product.setCountry(countryRepository.findByName("Ukraine"));
        assert(productRepository.count() == 10);
        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
        assert(productRepository.count() == 11);
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void deleteProduct() {
    }
}