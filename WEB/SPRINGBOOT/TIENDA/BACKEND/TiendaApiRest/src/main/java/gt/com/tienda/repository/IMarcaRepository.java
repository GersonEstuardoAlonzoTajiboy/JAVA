package gt.com.tienda.repository;

import gt.com.tienda.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMarcaRepository extends JpaRepository<MarcaEntity, Long> {
    List<MarcaEntity> findAllByEstadoIsTrue();

    MarcaEntity findByMarcaIdAndEstadoIsTrue(Long marcaId);
}
