package com.clientes.automotriz.exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    private String status;
    private int code;
    private String message;
}
