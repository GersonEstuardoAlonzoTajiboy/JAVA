package gt.com.tienda.service;

import gt.com.tienda.dto.DepartamentoDTO;
import gt.com.tienda.entity.DepartamentoEntity;
import gt.com.tienda.mapper.DepartamentoMapper;
import gt.com.tienda.repository.IDepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements IDepartamentoService {
    private final DepartamentoMapper departamentoMapper;
    private final IDepartamentoRepository iDepartamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DepartamentoDTO> getAllDepartamentos() {
        List<DepartamentoEntity> departamentoEntityList = iDepartamentoRepository.findAllByEstadoIsTrueAndPaisEntity_EstadoIsTrue();
        if (departamentoEntityList != null) {
            return departamentoEntityList.stream().map(departamentoMapper::convertToDto).collect(toList());
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
        DepartamentoEntity departamentoEntityNew = departamentoMapper.convertToEntity(departamentoDTO);
        if (departamentoEntityNew != null) {
            departamentoEntityNew.setNombre(departamentoDTO.getNombre());
            departamentoEntityNew.setPaisEntity(departamentoDTO.getPaisEntity());
            departamentoEntityNew.setFechaCreacion(now());
            departamentoEntityNew.setFechaModificacion(null);
            departamentoEntityNew.setEstado(true);
            iDepartamentoRepository.save(departamentoEntityNew);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntityNew);
    }

    @Override
    @Transactional
    public DepartamentoDTO updateDepartamento(DepartamentoDTO departamentoDTO) {
        DepartamentoEntity departamentoEntityExist = iDepartamentoRepository.findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(departamentoDTO.getDepartamentoId());
        if (departamentoEntityExist != null) {
            departamentoEntityExist.setNombre(departamentoDTO.getNombre());
            departamentoEntityExist.setPaisEntity(departamentoDTO.getPaisEntity());
            departamentoEntityExist.setFechaModificacion(now());
            iDepartamentoRepository.save(departamentoEntityExist);
        } else {
            return null;
        }
        return departamentoMapper.convertToDto(departamentoEntityExist);
    }

    @Override
    @Transactional
    public DepartamentoDTO deactiveDepartamentoById(Long departamentoId) {
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
