package gt.com.tienda.dto;

import gt.com.tienda.entity.DepartamentoEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MunicipioDTO {
    private Long municipioId;
    private String nombre;
    private DepartamentoEntity departamentoEntity;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private boolean estado;
}
