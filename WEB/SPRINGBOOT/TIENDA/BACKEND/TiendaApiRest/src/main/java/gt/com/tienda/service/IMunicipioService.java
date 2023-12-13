package gt.com.tienda.service;

import gt.com.tienda.dto.MunicipioDTO;

import java.util.List;

public interface IMunicipioService {
    List<MunicipioDTO> getAllMunicipios();

    MunicipioDTO getMunicipioById(Long municipioId);

    MunicipioDTO createMunicipio(MunicipioDTO municipioDTO);

    MunicipioDTO updateMunicipio(MunicipioDTO municipioDTO);

    MunicipioDTO deactiveMunicipioById(Long municipioId);

    MunicipioDTO deleteMunicipioById(Long municipioId);
}
