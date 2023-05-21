package ua.volcaniccupcake.onlinestore.model.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ItemDTO {
    private Integer number;
    private ProductDTO product;
}
