package gt.com.tienda.service;

import gt.com.tienda.dto.ProveedorDTO;
import gt.com.tienda.entity.ProveedorEntity;
import gt.com.tienda.mapper.ProveedorMapper;
import gt.com.tienda.repository.IProveedorRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
            proveedorEntityNew.setFechaCreacion(LocalDate.now());
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
        return null;
    }

    @Override
    @Transactional
    public ProveedorDTO deactiveProveedorById(Long proveedorId) {
        return null;
    }

    @Override
    @Transactional
    public ProveedorDTO deleteProveedorById(Long proveedorId) {
        return null;
    }
}
