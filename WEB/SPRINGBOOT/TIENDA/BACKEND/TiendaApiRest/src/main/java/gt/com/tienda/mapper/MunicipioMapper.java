package gt.com.tienda.mapper;

import gt.com.tienda.dto.MunicipioDTO;
import gt.com.tienda.entity.MunicipioEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MunicipioMapper {
    private final ModelMapper modelMapper;

    public MunicipioDTO convertToDto(MunicipioEntity municipioEntity) {
        return modelMapper.map(municipioEntity, MunicipioDTO.class);
    }

    public MunicipioEntity convertToEntity(MunicipioDTO municipioDTO) {
        return modelMapper.map(municipioDTO, MunicipioEntity.class);
    }
}
