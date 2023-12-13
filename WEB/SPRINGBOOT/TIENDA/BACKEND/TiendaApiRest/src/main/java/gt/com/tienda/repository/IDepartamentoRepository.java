package gt.com.tienda.repository;

import gt.com.tienda.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {
    List<DepartamentoEntity> findAllByEstadoIsTrueAndPaisEntity_EstadoIsTrue();

    DepartamentoEntity findByDepartamentoIdAndEstadoIsTrueAndPaisEntity_EstadoIsTrue(Long departamentoId);
}
