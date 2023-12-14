package gt.com.tienda.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeneroDTO {
    private Long generoId;
    private String nombre;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private boolean estado;
}
