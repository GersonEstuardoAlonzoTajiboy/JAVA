package gt.com.tienda.repository;

import gt.com.tienda.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGeneroRepository extends JpaRepository<GeneroEntity, Long> {
    List<GeneroEntity> findAllByEstadoIsTrue();

    GeneroEntity findByGeneroIdAndEstadoIsTrue(Long generoId);
}
