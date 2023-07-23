package ua.volcaniccupcake.onlinestore.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class OrderDTO {
    private Long id;

    private Set<ItemDTO> items;
}
