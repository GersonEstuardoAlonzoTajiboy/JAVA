package gt.com.tienda.service;

import gt.com.tienda.dto.MunicipioDTO;
import gt.com.tienda.entity.MunicipioEntity;
import gt.com.tienda.mapper.MunicipioMapper;
import gt.com.tienda.repository.IMunicipioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements IMunicipioService {
    private final MunicipioMapper municipioMapper;
    private final IMunicipioRepository iMunicipioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MunicipioDTO> getAllMunicipios() {
        List<MunicipioEntity> municipioEntityList = iMunicipioRepository.findAllByEstadoIsTrue();
        if (municipioEntityList != null) {
            return municipioEntityList.stream().map(municipioMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MunicipioDTO getMunicipioById(Long municipioId) {
        MunicipioEntity municipioEntityExist = iMunicipioRepository.findByMunicipioIdAndEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue(municipioId);
        if (municipioEntityExist != null) {
            return municipioMapper.convertToDto(municipioEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public MunicipioDTO createMunicipio(MunicipioDTO municipioDTO) {
        MunicipioEntity municipioEntityNew = municipioMapper.convertToEntity(municipioDTO);
        if (municipioEntityNew != null) {
            municipioEntityNew.setNombre(municipioDTO.getNombre());
            municipioEntityNew.setDepartamentoEntity(municipioDTO.getDepartamentoEntity());
            municipioEntityNew.setFechaCreacion(now());
            municipioEntityNew.setFechaModificacion(null);
            municipioEntityNew.setEstado(true);
            iMunicipioRepository.save(municipioEntityNew);
        } else {
            return null;
        }
        return municipioMapper.convertToDto(municipioEntityNew);
    }

    @Override
    @Transactional
    public MunicipioDTO updateMunicipio(MunicipioDTO municipioDTO) {
        MunicipioEntity municipioEntityExist = iMunicipioRepository.findByMunicipioIdAndEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue(municipioDTO.getMunicipioId());
        if (municipioEntityExist != null) {
            municipioEntityExist.setNombre(municipioDTO.getNombre());
            municipioEntityExist.setDepartamentoEntity(municipioDTO.getDepartamentoEntity());
            municipioEntityExist.setFechaModificacion(now());
            iMunicipioRepository.save(municipioEntityExist);
        } else {
            return null;
        }
        return municipioMapper.convertToDto(municipioEntityExist);
    }

    @Override
    @Transactional
    public MunicipioDTO deactiveMunicipioById(Long municipioId) {
        MunicipioEntity municipioEntityExist = iMunicipioRepository.findByMunicipioIdAndEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue(municipioId);
        if (municipioEntityExist != null) {
            municipioEntityExist.setEstado(false);
            iMunicipioRepository.save(municipioEntityExist);
        } else {
            return null;
        }
        return municipioMapper.convertToDto(municipioEntityExist);
    }

    @Override
    @Transactional
    public MunicipioDTO deleteMunicipioById(Long municipioId) {
        MunicipioEntity municipioEntityExist = iMunicipioRepository.findByMunicipioIdAndEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue(municipioId);
        if (municipioEntityExist != null) {
            iMunicipioRepository.delete(municipioEntityExist);
        } else {
            return null;
        }
        return municipioMapper.convertToDto(municipioEntityExist);
    }
}
