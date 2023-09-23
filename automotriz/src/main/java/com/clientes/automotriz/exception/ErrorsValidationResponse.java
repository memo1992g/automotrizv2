package com.clientes.automotriz.exception;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsValidationResponse{
        private List<String> errors;
        private String status;
        private int code;
}
