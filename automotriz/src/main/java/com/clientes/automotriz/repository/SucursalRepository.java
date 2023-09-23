package com.clientes.automotriz.repository;

import com.clientes.automotriz.entity.Cliente;
import com.clientes.automotriz.entity.Sucursal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SucursalRepository extends CrudRepository<Sucursal, Integer> {
}
