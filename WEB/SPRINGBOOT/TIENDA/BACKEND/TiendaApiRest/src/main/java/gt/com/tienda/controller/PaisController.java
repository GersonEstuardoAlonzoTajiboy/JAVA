package gt.com.tienda.controller;

import gt.com.tienda.dto.PaisDTO;
import gt.com.tienda.service.IPaisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pais")
public class PaisController {
    private final IPaisService iPaisService;

    @GetMapping
    public ResponseEntity<List<PaisDTO>> getAllPaises() {
        List<PaisDTO> paisDTOList = iPaisService.getAllPaises();
        if (paisDTOList != null && !paisDTOList.isEmpty()) {
            return ResponseEntity.ok(paisDTOList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{paisId}")
    public ResponseEntity<PaisDTO> getPaisById(@PathVariable("paisId") Long paisId) {
        PaisDTO paisDTO = iPaisService.getPaisById(paisId);
        if (paisDTO != null) {
            return ResponseEntity.ok(paisDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<PaisDTO> createPais(@Valid @RequestBody PaisDTO paisDTO) {
        PaisDTO paisDTONew = iPaisService.createPais(paisDTO);
        if (paisDTONew != null) {
            return ResponseEntity.ok(paisDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<PaisDTO> updatePais(@Valid @RequestBody PaisDTO paisDTO) {
        PaisDTO paisDTOExist = iPaisService.updatePais(paisDTO);
        if (paisDTOExist != null) {
            return ResponseEntity.ok(paisDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{paisId}")
    public ResponseEntity<PaisDTO> deactivePaisById(@PathVariable("paisId") Long paisId) {
        PaisDTO paisDTOExist = iPaisService.deactivePaisById(paisId);
        if (paisDTOExist != null) {
            return ResponseEntity.ok(paisDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{paisId}")
    public ResponseEntity<PaisDTO> deletePaisById(@PathVariable("paisId") Long paisId) {
        PaisDTO paisDTOExist = iPaisService.deletePaisById(paisId);
        if (paisDTOExist != null) {
            return ResponseEntity.ok(paisDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
