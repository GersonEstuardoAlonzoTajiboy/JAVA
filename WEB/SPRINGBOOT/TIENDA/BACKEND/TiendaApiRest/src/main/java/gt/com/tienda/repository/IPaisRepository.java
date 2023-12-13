package gt.com.tienda.repository;

import gt.com.tienda.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaisRepository extends JpaRepository<PaisEntity, Long> {
    List<PaisEntity> findAllByEstadoIsTrue();

    PaisEntity findByPaisIdAndEstadoIsTrue(Long paisId);
}
