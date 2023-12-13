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
@Table(name = "municipio")
public class MunicipioEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "municipio_id", nullable = false)
    private Long municipioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamentoEntity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDate fechaModificacion;

    @Column(name = "estado", nullable = false)
    private boolean estado;
}
