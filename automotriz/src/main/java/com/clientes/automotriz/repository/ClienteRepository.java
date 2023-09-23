package com.clientes.automotriz.repository;

import com.clientes.automotriz.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c INNER JOIN  Sucursal  s WHERE c.estado = 1")
    List<Cliente> getAllActiveCustomer();
    List<Cliente> findBySucursal_Localidad(String localidad);
}
