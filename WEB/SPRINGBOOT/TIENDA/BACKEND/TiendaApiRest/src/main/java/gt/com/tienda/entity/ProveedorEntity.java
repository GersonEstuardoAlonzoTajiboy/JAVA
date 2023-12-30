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
@Table(name = "proveedor")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "proveedor_id", nullable = false)
    private Long proveedorId;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroEntity generoEntity;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotEmpty
    @Size(min = 13, max = 13, message = "13 caracteres como minimo y 13 caracteres como maximo")
    @Column(name = "dpi", nullable = false)
    private String dpi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamentoEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private MunicipioEntity municipioEntity;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotEmpty
    @Size(min = 12, max = 12, message = "12 caracteres como minimo y 12 caracteres como maximo")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotEmpty
    @Size(min = 5, max = 50, message = "5 caracteres como minimo y 50 caracteres como maximo")
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @NotNull
    @OneToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private UsuarioEntity creadorPor;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToOne
    @JoinColumn(name = "modificado_por")
    private UsuarioEntity modificadoPor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private boolean estado;
}
