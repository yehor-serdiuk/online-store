package ua.volcaniccupcake.onlinestore.model.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProductDTO {

    @Null(message = "id value cannot be non-null")
    private Long id;

    @Size(min = 2, max = 32, message = "name cannot be shorter than 2 or longer than 32")
    private String name;

    @Size(min = 2, max = 32, message = "name cannot be shorter than 2 or longer than 32")
    private String countryName;
}
