package gt.com.tienda.service;

import gt.com.tienda.dto.PaisDTO;
import gt.com.tienda.entity.PaisEntity;
import gt.com.tienda.mapper.PaisMapper;
import gt.com.tienda.repository.IPaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class PaisServiceImpl implements IPaisService {
    private final PaisMapper paisMapper;
    private final IPaisRepository iPaisRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PaisDTO> getAllPaises() {
        List<PaisEntity> paisEntities = iPaisRepository.findAllByEstadoIsTrue();
        if (paisEntities != null) {
            return paisEntities.stream().map(paisMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PaisDTO getPaisById(Long paisId) {
        PaisEntity paisEntity = iPaisRepository.findByPaisIdAndEstadoIsTrue(paisId);
        if (paisEntity != null) {
            return paisMapper.convertToDto(paisEntity);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public PaisDTO createPais(PaisDTO paisDTO) {
        PaisEntity paisEntity = paisMapper.convertToEntity(paisDTO);
        if (paisEntity != null) {
            paisEntity.setNombre(paisDTO.getNombre());
            paisEntity.setFechaCreacion(LocalDate.now());
            paisEntity.setFechaModificacion(null);
            paisEntity.setEstado(true);
            iPaisRepository.save(paisEntity);
        } else {
            return null;
        }
        return paisMapper.convertToDto(paisEntity);
    }

    @Override
    @Transactional
    public PaisDTO updatePais(PaisDTO paisDTO) {
        PaisEntity paisEntityExist = iPaisRepository.findByPaisIdAndEstadoIsTrue(paisDTO.getPaisId());
        if (paisEntityExist != null) {
            paisEntityExist.setNombre(paisDTO.getNombre());
            paisEntityExist.setFechaModificacion(LocalDate.now());
            iPaisRepository.save(paisEntityExist);
        } else {
            return null;
        }
        return paisMapper.convertToDto(paisEntityExist);
    }

    @Override
    @Transactional
    public PaisDTO deactivePais(Long paisId) {
        PaisEntity paisEntityExist = iPaisRepository.findByPaisIdAndEstadoIsTrue(paisId);
        if (paisEntityExist != null) {
            paisEntityExist.setEstado(false);
            iPaisRepository.save(paisEntityExist);
        } else {
            return null;
        }
        return paisMapper.convertToDto(paisEntityExist);
    }

    @Override
    @Transactional
    public PaisDTO deletePaisById(Long paisId) {
        PaisEntity paisEntityExist = iPaisRepository.findByPaisIdAndEstadoIsTrue(paisId);
        if (paisEntityExist != null) {
            iPaisRepository.delete(paisEntityExist);
        } else {
            return null;
        }
        return paisMapper.convertToDto(paisEntityExist);
    }
}
