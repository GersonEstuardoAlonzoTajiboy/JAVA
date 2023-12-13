package gt.com.tienda.service;

import gt.com.tienda.dto.DepartamentoDTO;
import gt.com.tienda.entity.DepartamentoEntity;
import gt.com.tienda.mapper.DepartamentoMapper;
import gt.com.tienda.repository.IDepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements IDepartamentoService {
    private final DepartamentoMapper departamentoMapper;
    private final IDepartamentoRepository iDepartamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> getAllDepartamentos() {
        List<DepartamentoEntity> departamentoEntities = iDepartamentoRepository.findAllByEstadoIsTrueAndPaisEntity_EstadoIsTrue();
        if (departamentoEntities != null) {
            return departamentoEntities.stream().map(departamentoMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DepartamentoDTO getDepartamentoById(Long departamentoId) {
        DepartamentoEntity departamentoEntityExist = iDepartamentoRepository.findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(departamentoId);
        if (departamentoEntityExist != null) {
            return departamentoMapper.convertToDto(departamentoEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public DepartamentoDTO createDepartamento(DepartamentoDTO departamentoDTO) {
        DepartamentoEntity departamentoEntity = departamentoMapper.convertToEntity(departamentoDTO);
        if (departamentoEntity != null) {
            departamentoEntity.setNombre(departamentoDTO.getNombre());
            departamentoEntity.setPaisEntity(departamentoDTO.getPaisEntity());
            departamentoEntity.setFechaCreacion(LocalDate.now());
            departamentoEntity.setFechaModificacion(null);
            departamentoEntity.setEstado(true);
            iDepartamentoRepository.save(departamentoEntity);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntity);
    }

    @Override
    @Transactional
    public DepartamentoDTO updateDepartamento(DepartamentoDTO departamentoDTO) {
        DepartamentoEntity departamentoEntityExist = iDepartamentoRepository.findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(departamentoDTO.getDepartamentoId());
        if (departamentoEntityExist != null) {
            departamentoEntityExist.setNombre(departamentoDTO.getNombre());
            departamentoEntityExist.setPaisEntity(departamentoDTO.getPaisEntity());
            departamentoEntityExist.setFechaModificacion(LocalDate.now());
            iDepartamentoRepository.save(departamentoEntityExist);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntityExist);
    }

    @Override
    @Transactional
    public DepartamentoDTO deactiveDepartamento(Long departamentoId) {
        DepartamentoEntity departamentoEntityExist = iDepartamentoRepository.findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(departamentoId);
        if (departamentoEntityExist != null) {
            departamentoEntityExist.setEstado(false);
            iDepartamentoRepository.save(departamentoEntityExist);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntityExist);
    }

    @Override
    @Transactional
    public DepartamentoDTO deleteDepartamentoById(Long departamentoId) {
        DepartamentoEntity departamentoEntityExist = iDepartamentoRepository.findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(departamentoId);
        if (departamentoEntityExist != null) {
            iDepartamentoRepository.delete(departamentoEntityExist);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntityExist);
    }
}
