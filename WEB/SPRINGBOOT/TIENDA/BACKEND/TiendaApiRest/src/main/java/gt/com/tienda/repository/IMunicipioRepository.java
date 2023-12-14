package gt.com.tienda.repository;

import gt.com.tienda.entity.MunicipioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMunicipioRepository extends JpaRepository<MunicipioEntity, Long> {
    List<MunicipioEntity> findAllByEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue();

    MunicipioEntity findByMunicipioIdAndEstadoIsTrueAndDepartamentoEntity_EstadoIsTrue(Long municipioId);
}
