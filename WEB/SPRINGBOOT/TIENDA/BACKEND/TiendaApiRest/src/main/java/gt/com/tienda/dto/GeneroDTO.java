package gt.com.tienda.dto;

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
public class GeneroDTO {
    @NotNull
    private Long generoId;
    @NotEmpty
    @Size(min = 8, max = 9, message = "8 caracteres como minimo y 9 caracteres como maximo")
    private String nombre;
    @NotNull
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    @NotNull
    private boolean estado;
}
