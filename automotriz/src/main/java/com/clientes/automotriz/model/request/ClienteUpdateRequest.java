package com.clientes.automotriz.model.request;

import lombok.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteUpdateRequest {
    Map<String, Object> camposActualizados;
}
