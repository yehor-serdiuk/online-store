package ua.volcaniccupcake.onlinestore.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.volcaniccupcake.onlinestore.model.Item;
import ua.volcaniccupcake.onlinestore.model.dto.ItemDTO;

import java.util.Set;

@Mapper(uses = ProductMapper.class)
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDTO itemToItemDTO(Item item);

    Item itemDTOToItem(ItemDTO dto);

    Set<Item> itemDTOSetToItemSet(Set<ItemDTO> itemDTOSet);
}
