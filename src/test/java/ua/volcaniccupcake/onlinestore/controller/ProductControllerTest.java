package ua.volcaniccupcake.onlinestore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.volcaniccupcake.onlinestore.model.Country;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.service.ProductService;
import ua.volcaniccupcake.onlinestore.service.ProductServiceImpl;

import javax.swing.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;


    @Test
    void getAllProduct() throws Exception {
        assertNotNull(productService.getProduct());
        mvc.perform(get("/product/"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() {
    }

    @Test
    void createProduct() {
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