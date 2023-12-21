package gt.com.tienda.controller;

import gt.com.tienda.dto.GeneroDTO;
import gt.com.tienda.service.IGeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/genero")
public class GeneroController {
    private final IGeneroService iGeneroService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAllGeneros() {
        List<GeneroDTO> generoDTOListExist = iGeneroService.getAllGeneros();
        if (generoDTOListExist != null) {
            return ResponseEntity.ok(generoDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{generoId}")
    public ResponseEntity<GeneroDTO> getGeneroById(@PathVariable("generoId") Long generoId) {
        GeneroDTO generoDTOExist = iGeneroService.getGeneroById(generoId);
        if (generoDTOExist != null) {
            return ResponseEntity.ok(generoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> createGenero(@Valid @RequestBody GeneroDTO generoDTO) {
        GeneroDTO generoDTONew = iGeneroService.createGenero(generoDTO);
        if (generoDTONew != null) {
            return ResponseEntity.ok(generoDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<GeneroDTO> updateGenero(@Valid @RequestBody GeneroDTO generoDTO) {
        GeneroDTO generoDTOExist = iGeneroService.updateGenero(generoDTO);
        if (generoDTOExist != null) {
            return ResponseEntity.ok(generoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{generoId}")
    public ResponseEntity<GeneroDTO> deactiveGeneroById(@PathVariable("generoId") Long generoId) {
        GeneroDTO generoDTOExist = iGeneroService.deactiveGeneroById(generoId);
        if (generoDTOExist != null) {
            return ResponseEntity.ok(generoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{generoId}")
    public ResponseEntity<GeneroDTO> deleteGeneroById(@PathVariable("generoId") Long generoId) {
        GeneroDTO generoDTOExist = iGeneroService.deleteGeneroById(generoId);
        if (generoDTOExist != null) {
            return ResponseEntity.ok(generoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
