package gt.com.tienda.entity;

import jakarta.persistence.*;
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

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Column(name = "estado", nullable = false)
    private boolean estado;
}
