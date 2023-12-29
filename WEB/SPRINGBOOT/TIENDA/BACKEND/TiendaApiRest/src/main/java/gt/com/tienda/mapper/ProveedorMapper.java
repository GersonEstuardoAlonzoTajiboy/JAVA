package gt.com.tienda.mapper;

import gt.com.tienda.dto.ProveedorDTO;
import gt.com.tienda.entity.ProveedorEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProveedorMapper {
    private final ModelMapper modelMapper;

    public ProveedorDTO convertToDto(ProveedorEntity proveedorEntity) {
        return modelMapper.map(proveedorEntity, ProveedorDTO.class);
    }

    public ProveedorEntity convertToEntity(ProveedorDTO proveedorDTO) {
        return modelMapper.map(proveedorDTO, ProveedorEntity.class);
    }
}
