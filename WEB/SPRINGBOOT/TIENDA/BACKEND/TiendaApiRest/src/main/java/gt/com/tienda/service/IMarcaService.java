package gt.com.tienda.service;

import gt.com.tienda.dto.MarcaDTO;

import java.util.List;

public interface IMarcaService {
    List<MarcaDTO> getAllMarcas();

    MarcaDTO getMarcaById(Long marcaId);

    MarcaDTO createMarca(MarcaDTO marcaDTO);

    MarcaDTO updateMarca(MarcaDTO marcaDTO);

    MarcaDTO deactiveMarcaById(Long marcaId);

    MarcaDTO deleteMarcaById(Long marcaId);
}
