package gt.com.tienda.entity;

import gt.com.tienda.util.RolEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private GeneroEntity generoEntity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Size(min = 13, max = 13, message = "El numero DPI debe tener 13 digitos exactos")
    @Column(name = "dpi", nullable = false)
    private String dpi;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamentoEntity;

    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private MunicipioEntity municipioEntity;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
