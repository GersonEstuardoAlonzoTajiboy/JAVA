package gt.com.tienda.repository;

import gt.com.tienda.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    List<CategoriaEntity> findAllByEstadoIsTrue();

    CategoriaEntity findByCategoriaIdAndEstadoIsTrue(Long categoriaId);
}
