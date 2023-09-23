package com.clientes.automotriz.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalRequest {
    @NotBlank(message = "Campo no puede ser vacío")
    private String nombre;
    @NotBlank(message = "Campo no puede ser vacío")
    @Size( max = 8)
    private String telefono;
    @NotBlank(message = "Campo no puede ser vacío")
    private String localidad;
}
