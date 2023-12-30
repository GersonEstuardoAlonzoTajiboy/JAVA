package gt.com.tienda.dto;

import gt.com.tienda.entity.PaisEntity;
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
public class DepartamentoDTO {
    @NotNull
    private Long departamentoId;
    @NotEmpty
    @Size(min = 5, max = 75, message = "5 caracteres como minimo y 75 caracteres como maximo")
    private String nombre;
    @NotNull
    private PaisEntity paisEntity;
    @NotNull
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    @NotNull
    private boolean estado;
}
