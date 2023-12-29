package gt.com.tienda.service;

import gt.com.tienda.dto.ProveedorDTO;

import java.util.List;

public interface IProveedorService {
    List<ProveedorDTO> getAllProveedores();

    ProveedorDTO getProveedorById(Long proveedorId);

    ProveedorDTO createProveedor(ProveedorDTO proveedorDTO);

    ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO);

    ProveedorDTO deactiveProveedorById(Long proveedorId);

    ProveedorDTO deleteProveedorById(Long proveedorId);
}
