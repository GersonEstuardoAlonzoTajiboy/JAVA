package gt.com.tienda.service;

import gt.com.tienda.auth.RespuestaAuthenticacion;
import gt.com.tienda.auth.SolicitudAuthenticacion;
import gt.com.tienda.auth.SolicitudRegistro;

public interface IAuthenticacionService {
    RespuestaAuthenticacion registro(SolicitudRegistro solicitudRegistro);
    RespuestaAuthenticacion authenticado(SolicitudAuthenticacion solicitudAuthenticacion);
}
