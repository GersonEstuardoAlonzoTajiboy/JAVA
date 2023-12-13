package gt.com.tienda.service;

import gt.com.tienda.dto.DepartamentoDTO;

import java.util.List;

public interface IDepartamentoService {
    List<DepartamentoDTO> getAllDepartamentos();

    DepartamentoDTO getDepartamentoById(Long departamentoId);

    DepartamentoDTO createDepartamento(DepartamentoDTO departamentoDTO);

    DepartamentoDTO updateDepartamento(DepartamentoDTO departamentoDTO);

    DepartamentoDTO deactiveDepartamentoById(Long departamentoId);

    DepartamentoDTO deleteDepartamentoById(Long departamentoId);
}
