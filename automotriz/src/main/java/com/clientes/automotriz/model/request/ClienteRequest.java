package com.clientes.automotriz.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequest {
    @NotBlank(message = "Campo primer nombre no puede ser vacío")
    private String primerNombre;
    @NotBlank(message = "Campo segundo nombre no puede ser vacío")
    private String segundoNombre;
    @NotBlank(message = "Campo primer apellido no puede ser vacío")
    private String primerApellido;
    @NotBlank(message = "Campo segundo apellido no puede ser vacío")
    private String segundoApellido;
    @NotBlank(message = "Campo correo no puede ser vacío")
    @Email
    private String correo;
    @NotBlank(message = "Campo telefono no puede ser vacío")
    @Size( max = 8, message = "Campo no puede ser mayor a 8 numeros")
    private String telefono;
    @NotBlank
    private String direccion;
//   @JsonFormat(pattern = "dd/MM/yyyy")
//   @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    @Positive(message = "Id de sucursal no puede ser negativo")
    private Integer idSucursal;
}
