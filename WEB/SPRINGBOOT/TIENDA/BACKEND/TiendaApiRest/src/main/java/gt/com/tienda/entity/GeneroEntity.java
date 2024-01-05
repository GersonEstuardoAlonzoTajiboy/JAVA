package gt.com.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "genero")
public class GeneroEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "genero_id", nullable = false)
    private Long generoId;

    @NotEmpty
    @Size(min = 1, max = 9, message = "1 caracter como minimo y 9 caracteres como maximo")
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
