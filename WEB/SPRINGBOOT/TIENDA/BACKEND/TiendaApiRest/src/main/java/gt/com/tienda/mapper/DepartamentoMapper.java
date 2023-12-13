package gt.com.tienda.mapper;

import gt.com.tienda.dto.DepartamentoDTO;
import gt.com.tienda.entity.DepartamentoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartamentoMapper {
    private final ModelMapper modelMapper;

    public DepartamentoDTO convertToDto(DepartamentoEntity departamentoEntity) {
        return modelMapper.map(departamentoEntity, DepartamentoDTO.class);
    }

    public DepartamentoEntity convertToEntity(DepartamentoDTO departamentoDTO) {
        return modelMapper.map(departamentoDTO, DepartamentoEntity.class);
    }
}
