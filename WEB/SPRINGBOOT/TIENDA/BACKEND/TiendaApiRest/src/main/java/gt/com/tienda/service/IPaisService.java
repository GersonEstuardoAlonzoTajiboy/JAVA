package gt.com.tienda.service;

import gt.com.tienda.dto.PaisDTO;

import java.util.List;

public interface IPaisService {
    List<PaisDTO> getAllPaises();

    PaisDTO getPaisById(Long paisId);

    PaisDTO createPais(PaisDTO paisDTO);

    PaisDTO updatePais(PaisDTO paisDTO);

    PaisDTO deactivePaisById(Long paisId);

    PaisDTO deletePaisById(Long paisId);
}
