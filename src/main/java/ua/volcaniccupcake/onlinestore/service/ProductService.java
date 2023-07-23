package ua.volcaniccupcake.onlinestore.service;

import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    ProductDTO createProduct(ProductDTO product);
    ProductDTO getProductById(long productId);
    Iterable<ProductDTO> getProduct();
    void updateProduct(long productId, ProductDTO product);
    void deleteProductById(long productId);
    void deleteProduct();
}
