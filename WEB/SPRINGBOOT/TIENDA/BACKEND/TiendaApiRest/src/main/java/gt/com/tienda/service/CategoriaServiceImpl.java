package gt.com.tienda.service;

import gt.com.tienda.dto.CategoriaDTO;
import gt.com.tienda.entity.CategoriaEntity;
import gt.com.tienda.mapper.CategoriaMapper;
import gt.com.tienda.repository.ICategoriaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {
    private final HttpServletRequest httpServletRequest;
    private final IJwtService iJwtService;
    private final CategoriaMapper categoriaMapper;
    private final ICategoriaRepository iCategoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> getAllCategorias() {
        List<CategoriaEntity> categoriaEntityListExist = iCategoriaRepository.findAllByEstadoIsTrue();
        if (categoriaEntityListExist != null) {
            return categoriaEntityListExist.stream().map(categoriaMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO getCategoriaById(Long categoriaId) {
        CategoriaEntity categoriaEntityExist = iCategoriaRepository.findByCategoriaIdAndEstadoIsTrue(categoriaId);
        if (categoriaEntityExist != null) {
            return categoriaMapper.convertToDto(categoriaEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtService.getUsuario(jwtToken);
        CategoriaEntity categoriaEntityNew = categoriaMapper.convertToEntity(categoriaDTO);
        if (categoriaEntityNew != null) {
            categoriaEntityNew.setNombre(categoriaDTO.getNombre());
            categoriaEntityNew.setDescripcion(categoriaDTO.getDescripcion());
            categoriaEntityNew.setCreadoPor(usuarioLogueado);
            categoriaEntityNew.setFechaCreacion(now());
            categoriaEntityNew.setEstado(true);
            iCategoriaRepository.save(categoriaEntityNew);
        } else {
            return null;
        }
        return categoriaMapper.convertToDto(categoriaEntityNew);
    }

    @Override
    @Transactional
    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtService.getUsuario(jwtToken);
        CategoriaEntity categoriaEntityExist = iCategoriaRepository.findByCategoriaIdAndEstadoIsTrue(categoriaDTO.getCategoriaId());
        if (categoriaEntityExist != null) {
            categoriaEntityExist.setNombre(categoriaDTO.getNombre());
            categoriaEntityExist.setDescripcion(categoriaDTO.getDescripcion());
            categoriaEntityExist.setModificadorPor(usuarioLogueado);
            categoriaEntityExist.setFechaModificacion(now());
            iCategoriaRepository.save(categoriaEntityExist);
        } else {
            return null;
        }
        return categoriaMapper.convertToDto(categoriaEntityExist);
    }

    @Override
    @Transactional
    public CategoriaDTO deactiveCategoriaById(Long categoriaId) {
        CategoriaEntity categoriaEntityExist = iCategoriaRepository.findByCategoriaIdAndEstadoIsTrue(categoriaId);
        if (categoriaEntityExist != null) {
            categoriaEntityExist.setEstado(false);
            iCategoriaRepository.save(categoriaEntityExist);
        } else {
            return null;
        }
        return categoriaMapper.convertToDto(categoriaEntityExist);
    }

    @Override
    @Transactional
    public CategoriaDTO deleteCategoriaById(Long categoriaId) {
        CategoriaEntity categoriaEntityExist = iCategoriaRepository.findByCategoriaIdAndEstadoIsTrue(categoriaId);
        if (categoriaEntityExist != null) {
            iCategoriaRepository.delete(categoriaEntityExist);
        } else {
            return null;
        }
        return categoriaMapper.convertToDto(categoriaEntityExist);
    }
}
