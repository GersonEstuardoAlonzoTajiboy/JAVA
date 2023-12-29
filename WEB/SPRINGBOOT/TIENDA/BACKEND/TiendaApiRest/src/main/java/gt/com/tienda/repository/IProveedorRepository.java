package gt.com.tienda.repository;

import gt.com.tienda.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    List<ProveedorEntity> findAllByEstadoIsTrue();

    ProveedorEntity findByProveedorIdAndEstadoIsTrue(Long proveedorId);
}
