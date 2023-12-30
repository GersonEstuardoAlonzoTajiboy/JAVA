package gt.com.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pais")
public class PaisEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pais_id", nullable = false)
    private Long paisId;

    @NotEmpty
    @Size(min = 5, max = 75, message = "5 caracteres como minimo y 75 caracteres como maximo")
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;
}
