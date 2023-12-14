package gt.com.tienda.controller;

import gt.com.tienda.dto.MunicipioDTO;
import gt.com.tienda.service.IMunicipioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/municipio")
public class MunicipioController {
    private final IMunicipioService iMunicipioService;

    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> getAllMunicipios() {
        List<MunicipioDTO> municipioDTOListExist = iMunicipioService.getAllMunicipios();
        if (municipioDTOListExist != null) {
            return ResponseEntity.ok(municipioDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{municipioId}")
    public ResponseEntity<MunicipioDTO> getMunicipioById(@PathVariable("municipioId") Long municipioId) {
        MunicipioDTO municipioDTOExist = iMunicipioService.getMunicipioById(municipioId);
        if (municipioDTOExist != null) {
            return ResponseEntity.ok(municipioDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<MunicipioDTO> createMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) {
        MunicipioDTO municipioDTONew = iMunicipioService.createMunicipio(municipioDTO);
        if (municipioDTONew != null) {
            return ResponseEntity.ok(municipioDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<MunicipioDTO> updateMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) {
        MunicipioDTO municipioDTOExist = iMunicipioService.updateMunicipio(municipioDTO);
        if (municipioDTOExist != null) {
            return ResponseEntity.ok(municipioDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{municipioId}")
    public ResponseEntity<MunicipioDTO> deactiveMunicipioById(@PathVariable("municipioId") Long municipioId) {
        MunicipioDTO municipioDTOExist = iMunicipioService.deactiveMunicipioById(municipioId);
        if (municipioDTOExist != null) {
            return ResponseEntity.ok(municipioDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{municipioId}")
    public ResponseEntity<MunicipioDTO> deleteMunicipioById(@PathVariable("municipioId") Long municipioId) {
        MunicipioDTO municipioDTOExist = iMunicipioService.deleteMunicipioById(municipioId);
        if (municipioDTOExist != null) {
            return ResponseEntity.ok(municipioDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
