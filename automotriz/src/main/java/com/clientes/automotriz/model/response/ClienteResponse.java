package com.clientes.automotriz.model.response;

import com.clientes.automotriz.entity.Sucursal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {
    private Integer idCliente;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String telefono;
    private String direccion;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;
    private Integer idSucursal;
    private String sucursal;
}
