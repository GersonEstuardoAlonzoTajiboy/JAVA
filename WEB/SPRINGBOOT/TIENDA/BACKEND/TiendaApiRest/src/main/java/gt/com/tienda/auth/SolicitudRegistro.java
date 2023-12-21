package gt.com.tienda.auth;

import gt.com.tienda.entity.DepartamentoEntity;
import gt.com.tienda.entity.GeneroEntity;
import gt.com.tienda.entity.MunicipioEntity;
import gt.com.tienda.util.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRegistro {
    private String nombre;
    private String apellido;
    private GeneroEntity generoEntity;
    private LocalDate fechaNacimiento;
    private String dpi;
    private DepartamentoEntity departamentoEntity;
    private MunicipioEntity municipioEntity;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String contrasenia;
    private RolEnum rol;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private boolean estado;
}
