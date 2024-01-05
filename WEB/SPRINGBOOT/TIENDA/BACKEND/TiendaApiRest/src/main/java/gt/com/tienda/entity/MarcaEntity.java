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
@Table(name = "marca")
public class MarcaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "marca_id", nullable = false)
    private Long marcaId;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @NotEmpty
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @OneToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private UsuarioEntity creadoPor;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToOne
    @JoinColumn(name = "modificado_por")
    private UsuarioEntity modificadorPor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;
}
