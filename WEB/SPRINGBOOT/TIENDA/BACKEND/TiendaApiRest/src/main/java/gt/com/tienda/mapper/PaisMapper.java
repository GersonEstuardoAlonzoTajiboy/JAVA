package gt.com.tienda.mapper;

import gt.com.tienda.dto.PaisDTO;
import gt.com.tienda.entity.PaisEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaisMapper {
    private final ModelMapper modelMapper;

    public PaisDTO convertToDto(PaisEntity paisEntity) {
        return modelMapper.map(paisEntity, PaisDTO.class);
    }

    public PaisEntity convertToEntity(PaisDTO paisDTO) {
        return modelMapper.map(paisDTO, PaisEntity.class);
    }
}
