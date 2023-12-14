package gt.com.tienda.service;

import gt.com.tienda.dto.GeneroDTO;
import gt.com.tienda.entity.GeneroEntity;
import gt.com.tienda.mapper.GeneroMapper;
import gt.com.tienda.repository.IGeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements IGeneroService {
    private final GeneroMapper generoMapper;
    private final IGeneroRepository iGeneroRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> generoEntityListExist = iGeneroRepository.findAllByEstadoIsTrue();
        if (generoEntityListExist != null) {
            return generoEntityListExist.stream().map(generoMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public GeneroDTO getGeneroById(Long generoId) {
        GeneroEntity generoEntityExist = iGeneroRepository.findByGeneroIdAndEstadoIsTrue(generoId);
        if (generoEntityExist != null) {
            return generoMapper.convertToDto(generoEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public GeneroDTO createGenero(GeneroDTO generoDTO) {
        GeneroEntity generoEntityNew = generoMapper.convertToEntity(generoDTO);
        if (generoEntityNew != null) {
            generoEntityNew.setNombre(generoDTO.getNombre());
            generoEntityNew.setFechaCreacion(now());
            generoEntityNew.setFechaModificacion(null);
            generoEntityNew.setEstado(true);
            iGeneroRepository.save(generoEntityNew);
        } else {
            return null;
        }
        return generoMapper.convertToDto(generoEntityNew);
    }

    @Override
    @Transactional
    public GeneroDTO updateGenero(GeneroDTO generoDTO) {
        GeneroEntity generoEntityExist = iGeneroRepository.findByGeneroIdAndEstadoIsTrue(generoDTO.getGeneroId());
        if (generoEntityExist != null) {
            generoEntityExist.setNombre(generoDTO.getNombre());
            generoEntityExist.setFechaModificacion(now());
            iGeneroRepository.save(generoEntityExist);
        } else {
            return null;
        }
        return generoMapper.convertToDto(generoEntityExist);
    }

    @Override
    @Transactional
    public GeneroDTO deactiveGeneroById(Long generoId) {
        GeneroEntity generoEntityExist = iGeneroRepository.findByGeneroIdAndEstadoIsTrue(generoId);
        if (generoEntityExist != null) {
            generoEntityExist.setEstado(false);
            iGeneroRepository.save(generoEntityExist);
        } else {
            return null;
        }
        return generoMapper.convertToDto(generoEntityExist);
    }

    @Override
    @Transactional
    public GeneroDTO deleteGeneroById(Long generoId) {
        GeneroEntity generoEntityExist = iGeneroRepository.findByGeneroIdAndEstadoIsTrue(generoId);
        if (generoEntityExist != null) {
            iGeneroRepository.delete(generoEntityExist);
        } else {
            return null;
        }
        return generoMapper.convertToDto(generoEntityExist);
    }
}
