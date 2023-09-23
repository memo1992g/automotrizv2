package com.clientes.automotriz.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalResponse {
    private Integer id_sucursal;
    private String nombre;
    private String telefono;
    private String localidad;
}
