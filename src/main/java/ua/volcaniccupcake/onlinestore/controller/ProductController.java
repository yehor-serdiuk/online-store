package ua.volcaniccupcake.onlinestore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.service.ProductService;
import ua.volcaniccupcake.onlinestore.model.Product;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "This REST controller provides services to manage product in the Online Store Application")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all product available in the application")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Iterable<Product> getAllProduct() {
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides product details for the supplied product id")
    public Optional<Product> getProductById(@PathVariable("id") long productId) {
        return productService.getProductById(productId);
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates new product in the application")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates product in the application for a supplied product id")
    public void updateCourse(@PathVariable("id") long productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes product in the application for a supplied product id")
    void deleteProductById(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all product in the application")
    void deleteProduct() {
        productService.deleteProduct();
    }
}
