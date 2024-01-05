package gt.com.tienda.mapper;

import gt.com.tienda.dto.MarcaDTO;
import gt.com.tienda.entity.MarcaEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarcaMapper {
    private final ModelMapper modelMapper;

    public MarcaDTO convertToDto(MarcaEntity marcaEntity) {
        return modelMapper.map(marcaEntity, MarcaDTO.class);
    }

    public MarcaEntity convertToEntity(MarcaDTO marcaDTO) {
        return modelMapper.map(marcaDTO, MarcaEntity.class);
    }
}
