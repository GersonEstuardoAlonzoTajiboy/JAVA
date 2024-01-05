package gt.com.tienda.service;

import gt.com.tienda.dto.MarcaDTO;
import gt.com.tienda.entity.MarcaEntity;
import gt.com.tienda.mapper.MarcaMapper;
import gt.com.tienda.repository.IMarcaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements IMarcaService {
    private final HttpServletRequest httpServletRequest;
    private final IJwtService iJwtService;
    private final MarcaMapper marcaMapper;
    private final IMarcaRepository iMarcaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MarcaDTO> getAllMarcas() {
        List<MarcaEntity> marcaEntityListExist = iMarcaRepository.findAllByEstadoIsTrue();
        if (marcaEntityListExist != null) {
            return marcaEntityListExist.stream().map(marcaMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MarcaDTO getMarcaById(Long marcaId) {
        MarcaEntity marcaEntityExist = iMarcaRepository.findByMarcaIdAndEstadoIsTrue(marcaId);
        if (marcaEntityExist != null) {
            return marcaMapper.convertToDto(marcaEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public MarcaDTO createMarca(MarcaDTO marcaDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtService.getUsuario(jwtToken);
        MarcaEntity marcaEntityNew = marcaMapper.convertToEntity(marcaDTO);
        if (marcaEntityNew != null) {
            marcaEntityNew.setNombre(marcaDTO.getNombre());
            marcaEntityNew.setDescripcion(marcaDTO.getDescripcion());
            marcaEntityNew.setCreadoPor(usuarioLogueado);
            marcaEntityNew.setFechaCreacion(now());
            marcaEntityNew.setEstado(true);
            iMarcaRepository.save(marcaEntityNew);
        } else {
            return null;
        }
        return marcaMapper.convertToDto(marcaEntityNew);
    }

    @Override
    @Transactional
    public MarcaDTO updateMarca(MarcaDTO marcaDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtService.getUsuario(jwtToken);
        MarcaEntity marcaEntityExist = iMarcaRepository.findByMarcaIdAndEstadoIsTrue(marcaDTO.getMarcaId());
        if (marcaEntityExist != null) {
            marcaEntityExist.setNombre(marcaDTO.getNombre());
            marcaEntityExist.setDescripcion(marcaDTO.getDescripcion());
            marcaEntityExist.setModificadorPor(usuarioLogueado);
            marcaEntityExist.setFechaModificacion(now());
            iMarcaRepository.save(marcaEntityExist);
        } else {
            return null;
        }
        return marcaMapper.convertToDto(marcaEntityExist);
    }

    @Override
    @Transactional
    public MarcaDTO deactiveMarcaById(Long marcaId) {
        MarcaEntity marcaEntityExist = iMarcaRepository.findByMarcaIdAndEstadoIsTrue(marcaId);
        if (marcaEntityExist != null) {
            marcaEntityExist.setEstado(false);
            iMarcaRepository.save(marcaEntityExist);
        } else {
            return null;
        }
        return marcaMapper.convertToDto(marcaEntityExist);
    }

    @Override
    @Transactional
    public MarcaDTO deleteMarcaById(Long marcaId) {
        MarcaEntity marcaEntityExist = iMarcaRepository.findByMarcaIdAndEstadoIsTrue(marcaId);
        if (marcaEntityExist != null) {
            iMarcaRepository.delete(marcaEntityExist);
        } else {
            return null;
        }
        return marcaMapper.convertToDto(marcaEntityExist);
    }
}
