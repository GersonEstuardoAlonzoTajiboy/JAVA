package gt.com.tienda.mapper;

import gt.com.tienda.dto.GeneroDTO;
import gt.com.tienda.entity.GeneroEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneroMapper {
    private final ModelMapper modelMapper;

    public GeneroDTO convertToDto(GeneroEntity generoEntity) {
        return modelMapper.map(generoEntity, GeneroDTO.class);
    }

    public GeneroEntity convertToEntity(GeneroDTO generoDTO) {
        return modelMapper.map(generoDTO, GeneroEntity.class);
    }
}
