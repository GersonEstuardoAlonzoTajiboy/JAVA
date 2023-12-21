package gt.com.tienda.service;

import gt.com.tienda.auth.RespuestaAuthenticacion;
import gt.com.tienda.auth.SolicitudAuthenticacion;
import gt.com.tienda.auth.SolicitudRegistro;
import gt.com.tienda.dto.UsuarioDTO;
import gt.com.tienda.entity.UsuarioEntity;
import gt.com.tienda.mapper.UsuarioMapper;
import gt.com.tienda.repository.IUsuarioRepository;
import gt.com.tienda.util.RolEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticacionServiceImpl implements IAuthenticacionService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService iJwtService;
    private final UsuarioMapper usuarioMapper;
    private final IUsuarioRepository iUsuarioRepository;

    @Override
    @Transactional
    public RespuestaAuthenticacion registro(SolicitudRegistro solicitudRegistro) {
        var usuarioDTO = UsuarioDTO.builder()
                .nombre(solicitudRegistro.getNombre())
                .apellido(solicitudRegistro.getApellido())
                .generoEntity(solicitudRegistro.getGeneroEntity())
                .fechaNacimiento(solicitudRegistro.getFechaNacimiento())
                .dpi(solicitudRegistro.getDpi())
                .departamentoEntity(solicitudRegistro.getDepartamentoEntity())
                .municipioEntity(solicitudRegistro.getMunicipioEntity())
                .direccion(solicitudRegistro.getDireccion())
                .telefono(solicitudRegistro.getTelefono())
                .correoElectronico(solicitudRegistro.getCorreoElectronico())
                .contrasenia(passwordEncoder.encode(solicitudRegistro.getContrasenia()))
                .rol(RolEnum.ADMINISTRADOR)
                .fechaCreacion(LocalDate.now())
                .fechaModificacion(null)
                .estado(true)
                .build();
        UsuarioEntity usuarioEntity = usuarioMapper.convertToEntity(usuarioDTO);
        iUsuarioRepository.save(usuarioEntity);
        var jwtToken = iJwtService.generateToken(usuarioEntity);
        return RespuestaAuthenticacion.builder()
                .jwtToken(jwtToken)
                .build();
    }

    @Override
    @Transactional
    public RespuestaAuthenticacion authenticado(SolicitudAuthenticacion solicitudAuthenticacion) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        solicitudAuthenticacion.getCorreoElectronico(),
                        solicitudAuthenticacion.getContrasenia()
                )
        );
        var usuario = iUsuarioRepository.findByCorreoElectronicoAndEstadoIsTrue(solicitudAuthenticacion.getCorreoElectronico()).orElseThrow();
        var jwtToken = iJwtService.generateToken(usuario);
        return RespuestaAuthenticacion.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
