package gt.com.tienda.dto;

import gt.com.tienda.entity.DepartamentoEntity;
import gt.com.tienda.entity.GeneroEntity;
import gt.com.tienda.entity.MunicipioEntity;
import gt.com.tienda.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProveedorDTO {
    private Long proveedorId;
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
    private UsuarioEntity creadorPor;
    private LocalDate fechaCreacion;
    private UsuarioEntity modificadoPor;
    private LocalDate fechaModificacion;
    private boolean estado;
}
