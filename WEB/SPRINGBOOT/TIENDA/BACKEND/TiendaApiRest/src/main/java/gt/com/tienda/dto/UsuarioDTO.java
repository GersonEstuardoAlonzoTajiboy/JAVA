package gt.com.tienda.dto;

import gt.com.tienda.entity.DepartamentoEntity;
import gt.com.tienda.entity.GeneroEntity;
import gt.com.tienda.entity.MunicipioEntity;
import gt.com.tienda.util.RolEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsuarioDTO {
    @NotNull
    private Long usuarioId;
    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    private String nombre;
    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    private String apellido;
    @NotNull
    private GeneroEntity generoEntity;
    @NotNull
    private LocalDate fechaNacimiento;
    @NotEmpty
    @Size(min = 13, max = 13, message = "13 caracteres como minimo y 13 caracteres como maximo")
    private String dpi;
    @NotNull
    private DepartamentoEntity departamentoEntity;
    @NotNull
    private MunicipioEntity municipioEntity;
    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    private String direccion;
    @NotEmpty
    @Size(min = 12, max = 12, message = "12 caracteres como minimo y 12 caracteres como maximo")
    private String telefono;
    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    private String correoElectronico;
    @NotEmpty
    @Size(min = 75, max = 75, message = "75 caracteres como minimo y 75 caracteres como maximo")
    private String contrasenia;
    @NotNull
    private RolEnum rol;
    @NotNull
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    @NotNull
    private boolean estado;
}
