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
@Table(name = "municipio")
public class MunicipioEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "municipio_id", nullable = false)
    private Long municipioId;

    @NotEmpty
    @Size(min = 5, max = 75, message = "5 caracteres como minimo y 75 caracteres como maximo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamentoEntity;

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
