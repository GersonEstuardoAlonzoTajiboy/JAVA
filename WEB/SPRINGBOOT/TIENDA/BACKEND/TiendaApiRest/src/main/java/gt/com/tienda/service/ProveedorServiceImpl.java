package gt.com.tienda.service;

import gt.com.tienda.dto.ProveedorDTO;
import gt.com.tienda.entity.ProveedorEntity;
import gt.com.tienda.mapper.ProveedorMapper;
import gt.com.tienda.repository.IProveedorRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements IProveedorService {
    private final HttpServletRequest httpServletRequest;
    private final IJwtService iJwtService;
    private final ProveedorMapper proveedorMapper;
    private final IProveedorRepository iProveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDTO> getAllProveedores() {
        List<ProveedorEntity> proveedorEntityListExist = iProveedorRepository.findAllByEstadoIsTrue();
        if (proveedorEntityListExist != null) {
            return proveedorEntityListExist.stream().map(proveedorMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDTO getProveedorById(Long proveedorId) {
        ProveedorEntity proveedorEntityExist = iProveedorRepository.findByProveedorIdAndEstadoIsTrue(proveedorId);
        if (proveedorEntityExist != null) {
            return proveedorMapper.convertToDto(proveedorEntityExist);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogueado = iJwtService.getUsuario(jwtToken);
        ProveedorEntity proveedorEntityNew = proveedorMapper.convertToEntity(proveedorDTO);
        if (proveedorEntityNew != null) {
            proveedorEntityNew.setNombre(proveedorDTO.getNombre());
            proveedorEntityNew.setApellido(proveedorDTO.getApellido());
            proveedorEntityNew.setGeneroEntity(proveedorDTO.getGeneroEntity());
            proveedorEntityNew.setFechaNacimiento(proveedorDTO.getFechaNacimiento());
            proveedorEntityNew.setDpi(proveedorDTO.getDpi());
            proveedorEntityNew.setDepartamentoEntity(proveedorDTO.getDepartamentoEntity());
            proveedorEntityNew.setMunicipioEntity(proveedorDTO.getMunicipioEntity());
            proveedorEntityNew.setDireccion(proveedorDTO.getDireccion());
            proveedorEntityNew.setTelefono(proveedorDTO.getTelefono());
            proveedorEntityNew.setCorreoElectronico(proveedorDTO.getCorreoElectronico());
            proveedorEntityNew.setCreadorPor(usuarioLogueado);
            proveedorEntityNew.setFechaCreacion(now());
            proveedorEntityNew.setModificadoPor(null);
            proveedorEntityNew.setFechaModificacion(null);
            proveedorEntityNew.setEstado(true);
            iProveedorRepository.save(proveedorEntityNew);
        } else {
            return null;
        }
        return proveedorMapper.convertToDto(proveedorEntityNew);
    }

    @Override
    @Transactional
    public ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO) {
        String jwtToken = httpServletRequest.getHeader("Authorization").replace("Bearer ", "");
        var usuarioLogeado = iJwtService.getUsuario(jwtToken);
        ProveedorEntity proveedorEntityExist = iProveedorRepository.findByProveedorIdAndEstadoIsTrue(proveedorDTO.getProveedorId());
        if (proveedorEntityExist != null) {
            proveedorEntityExist.setNombre(proveedorDTO.getNombre());
            proveedorEntityExist.setApellido(proveedorDTO.getApellido());
            proveedorEntityExist.setGeneroEntity(proveedorDTO.getGeneroEntity());
            proveedorEntityExist.setFechaNacimiento(proveedorDTO.getFechaNacimiento());
            proveedorEntityExist.setDpi(proveedorDTO.getDpi());
            proveedorEntityExist.setDepartamentoEntity(proveedorDTO.getDepartamentoEntity());
            proveedorEntityExist.setMunicipioEntity(proveedorDTO.getMunicipioEntity());
            proveedorEntityExist.setDireccion(proveedorDTO.getDireccion());
            proveedorEntityExist.setTelefono(proveedorDTO.getTelefono());
            proveedorEntityExist.setCorreoElectronico(proveedorDTO.getCorreoElectronico());
            proveedorEntityExist.setModificadoPor(usuarioLogeado);
            proveedorEntityExist.setFechaModificacion(now());
            iProveedorRepository.save(proveedorEntityExist);
        } else {
            return null;
        }
        return proveedorMapper.convertToDto(proveedorEntityExist);
    }

    @Override
    @Transactional
    public ProveedorDTO deactiveProveedorById(Long proveedorId) {
        ProveedorEntity proveedorEntityExist = iProveedorRepository.findByProveedorIdAndEstadoIsTrue(proveedorId);
        if (proveedorEntityExist != null) {
            proveedorEntityExist.setEstado(false);
            iProveedorRepository.save(proveedorEntityExist);
        } else {
            return null;
        }
        return proveedorMapper.convertToDto(proveedorEntityExist);
    }

    @Override
    @Transactional
    public ProveedorDTO deleteProveedorById(Long proveedorId) {
        ProveedorEntity proveedorEntityExist = iProveedorRepository.findByProveedorIdAndEstadoIsTrue(proveedorId);
        if (proveedorEntityExist != null) {
            iProveedorRepository.delete(proveedorEntityExist);
        } else {
            return null;
        }
        return proveedorMapper.convertToDto(proveedorEntityExist);
    }
}
