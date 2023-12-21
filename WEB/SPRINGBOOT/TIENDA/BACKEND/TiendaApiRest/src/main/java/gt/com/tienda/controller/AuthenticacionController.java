package gt.com.tienda.controller;

import gt.com.tienda.auth.RespuestaAuthenticacion;
import gt.com.tienda.auth.SolicitudAuthenticacion;
import gt.com.tienda.auth.SolicitudRegistro;
import gt.com.tienda.service.IAuthenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthenticacionController {
    private final IAuthenticacionService iAuthenticacionService;

    @PostMapping("registro")
    public ResponseEntity<RespuestaAuthenticacion> registro(@Valid @RequestBody SolicitudRegistro solicitudRegistro) {
        if (solicitudRegistro != null) {
            return ResponseEntity.ok(iAuthenticacionService.registro(solicitudRegistro));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("authenticar")
    public ResponseEntity<RespuestaAuthenticacion> authenticado(@Valid @RequestBody SolicitudAuthenticacion solicitudAuthenticacion) {
        if (solicitudAuthenticacion != null) {
            return ResponseEntity.ok(iAuthenticacionService.authenticado(solicitudAuthenticacion));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
