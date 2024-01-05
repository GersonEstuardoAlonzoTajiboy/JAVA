package gt.com.tienda.dto;

import gt.com.tienda.entity.UsuarioEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaDTO {
    private Long categoriaId;
    private String nombre;
    private String descripcion;
    private UsuarioEntity creadoPor;
    private LocalDate fechaCreacion;
    private UsuarioEntity modificadorPor;
    private LocalDate fechaModificacion;
    private boolean estado;
}
