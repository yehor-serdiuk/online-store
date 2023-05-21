package ua.volcaniccupcake.onlinestore.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private List<ItemDTO> items;
}
