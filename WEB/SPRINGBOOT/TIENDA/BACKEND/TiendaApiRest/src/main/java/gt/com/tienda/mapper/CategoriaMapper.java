package gt.com.tienda.mapper;

import gt.com.tienda.dto.CategoriaDTO;
import gt.com.tienda.entity.CategoriaEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoriaMapper {
    private final ModelMapper modelMapper;

    public CategoriaDTO convertToDto(CategoriaEntity categoriaEntity) {
        return modelMapper.map(categoriaEntity, CategoriaDTO.class);
    }

    public CategoriaEntity convertToEntity(CategoriaDTO categoriaDTO) {
        return modelMapper.map(categoriaDTO, CategoriaEntity.class);
    }
}
