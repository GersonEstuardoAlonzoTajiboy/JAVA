package gt.com.tienda.controller;

import gt.com.tienda.dto.ProveedorDTO;
import gt.com.tienda.service.IProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {
    private final IProveedorService iProveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> getAllProveedores() {
        List<ProveedorDTO> proveedorDTOListExist = iProveedorService.getAllProveedores();
        if (proveedorDTOListExist != null) {
            return ResponseEntity.ok(proveedorDTOListExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{proveedorId}")
    public ResponseEntity<ProveedorDTO> getProveedorById(@PathVariable("proveedorId") Long proveedorId) {
        ProveedorDTO proveedorDTOExist = iProveedorService.getProveedorById(proveedorId);
        if (proveedorDTOExist != null) {
            return ResponseEntity.ok(proveedorDTOExist);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> createProveedor(@Valid @RequestBody ProveedorDTO proveedorDTO) {
        ProveedorDTO proveedorDTONew = iProveedorService.createProveedor(proveedorDTO);
        if (proveedorDTONew != null) {
            return ResponseEntity.ok(proveedorDTONew);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
