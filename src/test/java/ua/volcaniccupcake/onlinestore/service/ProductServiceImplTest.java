package ua.volcaniccupcake.onlinestore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.volcaniccupcake.onlinestore.exception.CountryNotFoundException;
import ua.volcaniccupcake.onlinestore.exception.ProductNotFoundException;
import ua.volcaniccupcake.onlinestore.model.Country;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.Order;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ProductMapper;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    Country spain;


    Product monitor;
    Product mouse;

    ProductDTO mouseDTO;

    @Mock
    ProductRepository productRepository;

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    ProductServiceImpl productService;


    static ProductMapper productMapper = ProductMapper.INSTANCE;

    static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        spain = Country.builder()
                .id(1L)
                .name("Spain")
                .productSet(new HashSet<>())
                .build();
        mouse = Product.builder()
                .id(1L)
                .name("mouse")
                .items(new HashSet<>())
                .country(spain)
                .build();
        monitor = Product.builder()
                .id(2L)
                .name("monitor")
                .items(new HashSet<>())
                .country(spain)
                .build();
        mouseDTO = productMapper.productToProductDTO(mouse);
    }

    @Test
    void testCreateProduct_whenCountryValid_returnProductDTO() {

        when(countryRepository.findByNameIgnoreCase(mouseDTO.getCountryName()))
                .thenReturn(Optional.of(spain));
        when(productRepository.save(any(Product.class)))
                .thenReturn(mouse);
        assertEquals(mouseDTO, productService.createProduct(mouseDTO));
    }
    @Test
    void testCreateProduct_whenCountryInvalid_throwsCountryNotFoundException() {

        when(countryRepository.findByNameIgnoreCase(mouseDTO.getCountryName()))
                .thenReturn(Optional.empty());
        assertThrows(CountryNotFoundException.class,
                () -> productService.createProduct(mouseDTO));
    }

    @Test
    void testGetProductById_whenValidId_returnMouseDTO() {
       when(productRepository.findById(1L))
               .thenReturn(Optional.of(mouse));
       assertEquals(mouseDTO, productService.getProductById(1L));
    }

    @Test
    void testGetProductById_whenInvalidId_throwProductNotFoundException() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> productService.getProductById(1L));
    }

    @Test
    void testGetProduct_returnProductDTOIterable() {
        Iterable<Product> productIterable = List.of(mouse, monitor);
        when(productRepository.findAll())
                .thenReturn(productIterable);

        assertIterableEquals(productMapper.productIterableToProductDTOIterable(productIterable),
                productService.getProduct());
    }

    @Test
    void testUpdateProduct_whenValidArguments_shouldNotThrow() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mouse));
        when(countryRepository.findByNameIgnoreCase(mouseDTO.getCountryName()))
                .thenReturn(Optional.of(spain));
        assertDoesNotThrow(() -> productService.updateProduct(1L, mouseDTO));
    }

    @Test
    void testUpdateProduct_whenInvalidId_throwsProductNotFoundException() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(1L, mouseDTO));
    }

    @Test
    void testUpdateProduct_whenInvalidCountryName_throwsCountryNotFoundException() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mouse));
        when(countryRepository.findByNameIgnoreCase(mouseDTO.getCountryName()))
                .thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class,
                () -> productService.updateProduct(1L, mouseDTO));
    }

    @Test
    void testDeleteProductById_whenValidId_doesNotThrow() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(mouse));

        assertDoesNotThrow(() -> productService.deleteProductById(1L));
    }

    @Test
    void testDeleteProductById_whenInvalidId_throwsProductNotFoundException() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class,
                () -> productService.deleteProductById(1L));
    }

    @Test
    void testDeleteProduct_doesNotThrow() {
        assertDoesNotThrow(() -> productService.deleteProduct());
    }
}