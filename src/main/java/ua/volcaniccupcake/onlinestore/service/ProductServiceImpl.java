package ua.volcaniccupcake.onlinestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.volcaniccupcake.onlinestore.exception.ProductNotFoundException;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Iterable<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(long productId, Product product) {
        productRepository.findById(productId).ifPresent(dbProduct -> {
            dbProduct.setName(product.getName());

            productRepository.save(dbProduct);
        });
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
}
