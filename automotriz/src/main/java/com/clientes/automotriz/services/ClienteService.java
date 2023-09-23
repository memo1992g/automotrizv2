package com.clientes.automotriz.services;

import com.clientes.automotriz.entity.Cliente;
import com.clientes.automotriz.model.request.ClienteRequest;
import com.clientes.automotriz.model.request.ClienteUpdateRequest;
import com.clientes.automotriz.model.response.ClienteResponse;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Optional<ClienteResponse> createCliente(ClienteRequest request);
  
    List<ClienteResponse> getAll(String localidad);
    Optional<ClienteResponse> getClienteById(Integer id);
    Optional<ClienteResponse> updateCliente(ClienteUpdateRequest request, Integer id);
    Optional<ClienteResponse> deleteCliente(Integer idCliente);

}
