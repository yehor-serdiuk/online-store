package ua.volcaniccupcake.onlinestore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.volcaniccupcake.onlinestore.exception.ProductNotFoundException;
import ua.volcaniccupcake.onlinestore.model.Country;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ProductMapper;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;
import ua.volcaniccupcake.onlinestore.service.ProductService;

import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ProductControllerTest {
    // Sample data
    Country spain;
    Product microphone;
    ProductDTO microphoneDTO;

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    ProductMapper productMapper = ProductMapper.INSTANCE;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        spain = Country.builder()
                .name("Spain")
                .build();
        microphone = Product.builder()
                .name("microphone")
                .country(spain)
                .build();
        microphoneDTO = productMapper.productToProductDTO(microphone);
    }


    @DisplayName("create product valid")
    @Test
    void testCreateProduct_whenValidArgumentAndHasCreatePermission_shouldReturnProductDTO() throws Exception {

        when(productService.createProduct(microphoneDTO))
                .thenReturn(microphoneDTO);
        mvc.perform(post("/api/product").with(httpBasic("admin", "password-admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(microphoneDTO)));
    }

    @DisplayName("create product no permission")
    @Test
    void testCreateProduct_whenNoCreatePermission_shouldReturnForbiddenCode() throws Exception {
        when(productService.createProduct(microphoneDTO))
                .thenReturn(microphoneDTO);
        mvc.perform(post("/api/product").with(httpBasic("restricted", "password-restricted"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isForbidden());
    }

    @DisplayName("create product unauthorized")
    @Test
    void testCreateProduct_whenUnauthorized_shouldReturnUnauthorizedCode() throws Exception {
        when(productService.createProduct(microphoneDTO))
                .thenReturn(microphoneDTO);
        mvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("create product invalid argument")
    @Test
    void testCreateProduct_whenInvalidArgument_shouldReturnBadRequest() throws Exception {
        ProductDTO productDTO1 = ProductDTO.builder()
                .id(1L) // Non null id
                .name("a") // Name shorter than 2
                .countryName("a") // Country name shorter than 2
                .build();
        ProductDTO productDTO2 = ProductDTO.builder()
                .id(1L) // Non null id
                .name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") // Name longer than 32
                .countryName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa") // Country name longer than 32
                .build();

        mvc.perform(post("/api/product").with(httpBasic("admin", "password-admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO1)))
                .andExpect(status().isBadRequest());

        mvc.perform(post("/api/product").with(httpBasic("admin", "password-admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDTO2)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("get product by id valid")
    @Test
    void testGetProductById_whenValidArgument_returnProduct() throws Exception {
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("oak table")
                .countryName("Norway")
                .build();
        when(productService.getProductById(1L))
                .thenReturn(productDTO);

        mvc.perform(get("/api/product/1"))
                .andExpect(content().json(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk());
    }

    @DisplayName("get product by id invalid")
    @Test
    void testGetProductById_whenInvalidArgument_returnNotFoundStatus() throws Exception {
        when(productService.getProductById(anyLong()))
                .thenThrow(new ProductNotFoundException(""));

        mvc.perform(get("/api/product/1"))
                .andExpect(status().isNotFound());
    }

    @DisplayName("get product")
    @Test
    void testGetProduct_returnAllProduct() throws Exception {

        Iterable<ProductDTO> productDTOIterable = List.of(
                ProductDTO.builder()
                        .id(1L)
                        .name("oak table")
                        .countryName("Norway")
                        .build(),
                ProductDTO.builder()
                        .id(2L)
                        .name("camera")
                        .countryName("Germany")
                        .build(),
                ProductDTO.builder()
                        .id(3L)
                        .name("microwave")
                        .countryName("China")
                        .build()
                );
        when(productService.getProduct())
                .thenReturn(productDTOIterable);

        mvc.perform(get("/api/product"))
                .andExpect(content().json(objectMapper.writeValueAsString(productDTOIterable)))
                .andExpect(status().isOk());
    }

    @DisplayName("update product valid")
    @Test
    void testUpdateProduct_whenValidArgumentAndHasCreatePermission_returnNoContentStatus() throws Exception {
        mvc.perform(put("/api/product/1").with(httpBasic("admin", "password-admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isNoContent());
    }

    @DisplayName("update product unauthorized")
    @Test
    void testUpdateProduct_whenUnauthorized_returnUnauthorizedCode() throws Exception {
        mvc.perform(put("/api/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("update product no permission")
    @Test
    void testUpdateProduct_whenNoCreatePermission_returnForbiddenCode() throws Exception {
        mvc.perform(put("/api/product/1").with(httpBasic("restricted", "password-restricted"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isForbidden());
    }

    @DisplayName("update product id invalid")
    @Test
    void testUpdateProduct_whenProductNotFound_returnNotFoundStatus() throws Exception {
        doThrow(new ProductNotFoundException(""))
                .when(productService).updateProduct(1L, microphoneDTO);

        mvc.perform(put("/api/product/1").with(httpBasic("admin", "password-admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(microphoneDTO)))
                .andExpect(status().isNotFound());
    }

    @DisplayName("delete by id product valid")
    @Test
    void testDeleteProductById_whenValidArgumentAndHasDeletePermission_returnNoContentStatus() throws Exception {
        mvc.perform(delete("/api/product/1").with(httpBasic("admin", "password-admin")))
                .andExpect(status().isNoContent());
    }

    @DisplayName("delete by id product unauthorized")
    @Test
    void testDeleteProductById_whenUnauthorized_returnUnauthorizedCode() throws Exception {
        mvc.perform(delete("/api/product/1"))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("delete by id product no permission")
    @Test
    void testDeleteProductById_whenNoCreatePermission_returnForbiddenCode() throws Exception {
        mvc.perform(delete("/api/product/1").with(httpBasic("restricted", "password-restricted")))
                .andExpect(status().isForbidden());
    }

    @DisplayName("delete by id product id invalid")
    @Test
    void testDeleteProductById_whenProductNotFound_returnNotFoundStatus() throws Exception {
        doThrow(new ProductNotFoundException(""))
                .when(productService).deleteProductById(1L);

        mvc.perform(delete("/api/product/1").with(httpBasic("admin", "password-admin")))
                .andExpect(status().isNotFound());
    }

    @DisplayName("delete product valid")
    @Test
    void testDeleteProduct_whenValidArgumentAndHasDeletePermission_returnNoContentStatus() throws Exception {
        mvc.perform(delete("/api/product").with(httpBasic("admin", "password-admin")))
                .andExpect(status().isNoContent());
    }

    @DisplayName("delete product unauthorized")
    @Test
    void testDeleteProduct_whenUnauthorized_returnUnauthorizedCode() throws Exception {
        mvc.perform(delete("/api/product"))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("delete product no permission")
    @Test
    void testDeleteProduct_whenNoCreatePermission_returnForbiddenCode() throws Exception {
        mvc.perform(delete("/api/product").with(httpBasic("restricted", "password-restricted")))
                .andExpect(status().isForbidden());
    }
}