package ua.volcaniccupcake.onlinestore.service;

import ua.volcaniccupcake.onlinestore.model.Product;

import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);
    Optional<Product> getProductById(long productId);
    Iterable<Product> getProduct();
    void updateProduct(long productId, Product product);
    void deleteProductById(long productId);
    void deleteProduct();
}
