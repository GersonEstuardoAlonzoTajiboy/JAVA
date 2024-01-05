package gt.com.tienda.controller;

import gt.com.tienda.dto.CategoriaDTO;
import gt.com.tienda.service.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    private final ICategoriaService iCategoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categoriaDTOListExist = iCategoriaService.getAllCategorias();
        if (categoriaDTOListExist != null) {
            return ResponseEntity.ok(categoriaDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{categoriaId}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("categoriaId") Long categoriaId) {
        CategoriaDTO categoriaDTOExist = iCategoriaService.getCategoriaById(categoriaId);
        if (categoriaDTOExist != null) {
            return ResponseEntity.ok(categoriaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaDTONew = iCategoriaService.createCategoria(categoriaDTO);
        if (categoriaDTONew != null) {
            return ResponseEntity.ok(categoriaDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> updateCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaDTOExist = iCategoriaService.updateCategoria(categoriaDTO);
        if (categoriaDTOExist != null) {
            return ResponseEntity.ok(categoriaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{categoriaId}")
    public ResponseEntity<CategoriaDTO> deactiveCategoriaById(@PathVariable("categoriaId") Long categoriaId) {
        CategoriaDTO categoriaDTOExist = iCategoriaService.deactiveCategoriaById(categoriaId);
        if (categoriaDTOExist != null) {
            return ResponseEntity.ok(categoriaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{categoriaId}")
    public ResponseEntity<CategoriaDTO> deleteCategoriaById(@PathVariable("categoriaId") Long categoriaId) {
        CategoriaDTO categoriaDTOExist = iCategoriaService.deleteCategoriaById(categoriaId);
        if (categoriaDTOExist != null) {
            return ResponseEntity.ok(categoriaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
