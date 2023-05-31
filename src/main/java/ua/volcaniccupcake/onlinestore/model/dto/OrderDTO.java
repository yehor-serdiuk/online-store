package ua.volcaniccupcake.onlinestore.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;

    private Set<ItemDTO> items;
}
