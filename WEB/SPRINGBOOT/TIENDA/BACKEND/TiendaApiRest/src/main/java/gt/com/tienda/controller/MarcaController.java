package gt.com.tienda.controller;

import gt.com.tienda.dto.MarcaDTO;
import gt.com.tienda.service.IMarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/marca")
public class MarcaController {
    private final IMarcaService iMarcaService;

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> getAllMarcas() {
        List<MarcaDTO> marcaDTOListExist = iMarcaService.getAllMarcas();
        if (marcaDTOListExist != null) {
            return ResponseEntity.ok(marcaDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{marcaId}")
    public ResponseEntity<MarcaDTO> getMarcaById(@PathVariable("marcaId") Long marcaId) {
        MarcaDTO marcaDTOExist = iMarcaService.getMarcaById(marcaId);
        if (marcaDTOExist != null) {
            return ResponseEntity.ok(marcaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> createMarca(@Valid @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO marcaDTONew = iMarcaService.createMarca(marcaDTO);
        if (marcaDTONew != null) {
            return ResponseEntity.ok(marcaDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<MarcaDTO> updateMarca(@Valid @RequestBody MarcaDTO marcaDTO) {
        MarcaDTO marcaDTOExist = iMarcaService.updateMarca(marcaDTO);
        if (marcaDTOExist != null) {
            return ResponseEntity.ok(marcaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{marcaId}")
    public ResponseEntity<MarcaDTO> deactiveMarcaById(@PathVariable("marcaId") Long marcaId) {
        MarcaDTO marcaDTOExist = iMarcaService.deactiveMarcaById(marcaId);
        if (marcaDTOExist != null) {
            return ResponseEntity.ok(marcaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{marcaId}")
    public ResponseEntity<MarcaDTO> deleteMarcaById(@PathVariable("marcaId") Long marcaId) {
        MarcaDTO marcaDTOExist = iMarcaService.deleteMarcaById(marcaId);
        if (marcaDTOExist != null) {
            return ResponseEntity.ok(marcaDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
