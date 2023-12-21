package gt.com.tienda.controller;

import gt.com.tienda.dto.DepartamentoDTO;
import gt.com.tienda.service.IDepartamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/departamento")
public class DepartamentoController {
    private final IDepartamentoService iDepartamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> getAllDepartamentos() {
        List<DepartamentoDTO> departamentoDTOListExist = iDepartamentoService.getAllDepartamentos();
        if (departamentoDTOListExist != null) {
            return ResponseEntity.ok(departamentoDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{departamentoId}")
    public ResponseEntity<DepartamentoDTO> getDepartamentoById(@PathVariable("departamentoId") Long departamentoId) {
        DepartamentoDTO departamentoDTOExist = iDepartamentoService.getDepartamentoById(departamentoId);
        if (departamentoDTOExist != null) {
            return ResponseEntity.ok(departamentoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> createDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO) {
        DepartamentoDTO departamentoDTONew = iDepartamentoService.createDepartamento(departamentoDTO);
        if (departamentoDTONew != null) {
            return ResponseEntity.ok(departamentoDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<DepartamentoDTO> updateDepartamento(@Valid @RequestBody DepartamentoDTO departamentoDTO) {
        DepartamentoDTO departamentoDTOExist = iDepartamentoService.updateDepartamento(departamentoDTO);
        if (departamentoDTOExist != null) {
            return ResponseEntity.ok(departamentoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("{departamentoId}")
    public ResponseEntity<DepartamentoDTO> deactiveDepartamentoById(@PathVariable("departamentoId") Long departamentoId) {
        DepartamentoDTO departamentoDTOExist = iDepartamentoService.deactiveDepartamentoById(departamentoId);
        if (departamentoDTOExist != null) {
            return ResponseEntity.ok(departamentoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{departamentoId}")
    public ResponseEntity<DepartamentoDTO> deleteDepartamentoById(@PathVariable("departamentoId") Long departamentoId) {
        DepartamentoDTO departamentoDTOExist = iDepartamentoService.deleteDepartamentoById(departamentoId);
        if (departamentoDTOExist != null) {
            return ResponseEntity.ok(departamentoDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
