package com.clientes.automotriz.services;

import com.clientes.automotriz.entity.Sucursal;
import com.clientes.automotriz.model.request.ClienteRequest;
import com.clientes.automotriz.model.request.SucursalRequest;
import com.clientes.automotriz.model.response.SucursalResponse;

import java.util.List;
import java.util.Optional;

public interface SucursalService {
    Optional<SucursalResponse> createSucursal(SucursalRequest request);
    List<SucursalResponse> getAll();
}
