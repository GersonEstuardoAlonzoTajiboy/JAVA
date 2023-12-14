package gt.com.tienda.service;

import gt.com.tienda.dto.GeneroDTO;

import java.util.List;

public interface IGeneroService {
    List<GeneroDTO> getAllGeneros();

    GeneroDTO getGeneroById(Long generoId);

    GeneroDTO createGenero(GeneroDTO generoDTO);

    GeneroDTO updateGenero(GeneroDTO generoDTO);

    GeneroDTO deactiveGeneroById(Long generoId);

    GeneroDTO deleteGeneroById(Long generoId);
}
