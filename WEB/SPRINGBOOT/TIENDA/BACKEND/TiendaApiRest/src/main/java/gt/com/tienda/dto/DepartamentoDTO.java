package gt.com.tienda.dto;

import gt.com.tienda.entity.PaisEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartamentoDTO {
    private Long departamentoId;
    private String nombre;
    private PaisEntity paisEntity;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private boolean estado;
}
