package ua.volcaniccupcake.onlinestore.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.volcaniccupcake.onlinestore.model.Product;
import ua.volcaniccupcake.onlinestore.model.dto.ProductDTO;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);;

    @Mapping(target = "countryName", source = "product.country.name")
    ProductDTO productToProductDTO(Product product);

    Iterable<ProductDTO> productIterableToProductDTOIterable(Iterable<Product> productIterable);
}
