package ua.volcaniccupcake.onlinestore.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.exception.CountryNotFoundException;
import ua.volcaniccupcake.onlinestore.exception.ProductNotFoundException;
import ua.volcaniccupcake.onlinestore.model.Country;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.model.mapper.ItemMapper;
import ua.volcaniccupcake.onlinestore.model.mapper.ProductMapper;
import ua.volcaniccupcake.onlinestore.repository.CountryRepository;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;
    private final CountryRepository countryRepository;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Country country = mapCountry(productDTO.getCountryName());

        Product product = productRepository.save(Product.builder()
                .name(productDTO.getName())
                .country(country)
                .build());
        return productMapper.productToProductDTO(product);
    }

    @Override
    public ProductDTO getProductById(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("product with id " + productId +" not found"));
        return productMapper.productToProductDTO(product);
    }

    @Override
    public Iterable<ProductDTO> getProduct() {
        return productMapper.productIterableToProductDTOIterable(productRepository.findAll());
    }

    @Override
    public void updateProduct(long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFoundException("product with id " + productId +" not found"));
        product.setName(productDTO.getName());
        product.setCountry(mapCountry(productDTO.getCountryName()));
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(long productId) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
                String.format("No product with id %s is available", productId)));
        productRepository.deleteById(productId);
    }

    @Override
    public void deleteProduct() {
        productRepository.deleteAll();
    }

    private Country mapCountry(String countryName) {
        return countryRepository.findByNameIgnoreCase(countryName)
                .orElseThrow(() -> new CountryNotFoundException("Country with name " + countryName + " not found"));
    }
}
