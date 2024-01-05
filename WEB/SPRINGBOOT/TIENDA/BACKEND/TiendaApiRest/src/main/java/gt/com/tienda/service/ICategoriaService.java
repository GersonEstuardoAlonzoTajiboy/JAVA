package gt.com.tienda.service;

import gt.com.tienda.dto.CategoriaDTO;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaDTO> getAllCategorias();

    CategoriaDTO getCategoriaById(Long categoriaId);

    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO deactiveCategoriaById(Long categoriaId);

    CategoriaDTO deleteCategoriaById(Long categoriaId);
}
