package ua.volcaniccupcake.onlinestore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;
import ua.volcaniccupcake.onlinestore.model.security.User;
import ua.volcaniccupcake.onlinestore.security.permissions.ProductCreatePermission;
import ua.volcaniccupcake.onlinestore.security.permissions.ProductDeletePermission;
import ua.volcaniccupcake.onlinestore.security.permissions.ProductReadPermission;
import ua.volcaniccupcake.onlinestore.security.permissions.ProductUpdatePermission;
import ua.volcaniccupcake.onlinestore.service.ProductService;
import ua.volcaniccupcake.onlinestore.model.Product;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "This REST controller provides services to manage product in the Online Store Application")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates new product in the application")
    @ProductCreatePermission
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all product available in the application")
    public Iterable<ProductDTO> getProduct() {
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides product details for the supplied product id")
    public ProductDTO getProductById(@PathVariable("id") long productId) {
        return productService.getProductById(productId);
    }


    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates product in the application for a supplied product id")
    @ProductUpdatePermission
    public void updateProduct(@PathVariable long id, @RequestBody @Valid ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes product in the application for a supplied product id")
    @ProductDeletePermission
    void deleteProductById(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all product in the application")
    @ProductDeletePermission
    void deleteProduct() {
        productService.deleteProduct();
    }
}
