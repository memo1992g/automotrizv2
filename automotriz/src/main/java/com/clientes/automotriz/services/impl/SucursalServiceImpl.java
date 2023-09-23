package com.clientes.automotriz.services.impl;

import com.clientes.automotriz.entity.Cliente;
import com.clientes.automotriz.entity.Sucursal;
import com.clientes.automotriz.model.request.ClienteRequest;
import com.clientes.automotriz.model.request.SucursalRequest;
import com.clientes.automotriz.model.response.ClienteResponse;
import com.clientes.automotriz.model.response.SucursalResponse;
import com.clientes.automotriz.repository.SucursalRepository;
import com.clientes.automotriz.services.SucursalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class SucursalServiceImpl implements SucursalService {
    private final SucursalRepository sucursalRepository;
    @Override
    public Optional<SucursalResponse> createSucursal(SucursalRequest request) {
        log.info("********** Obteniendo sucursal request {} **********", request);
        var sucursalToSave = Sucursal.builder()
                .telefono(request.getTelefono())
                .nombre(request.getNombre())
                .localidad(request.getLocalidad())
                .build();
        var sucursalSaved = sucursalRepository.save(sucursalToSave);
        log.info("********** Sucursal creada {} **********", sucursalResponse(sucursalSaved));
        return Optional.ofNullable(sucursalResponse(sucursalSaved));
    }

    @Override
    public List<SucursalResponse> getAll() {
        List<SucursalResponse> sucursalResponseList = new ArrayList<>();
        sucursalRepository.findAll().forEach( sucursal -> {
            var sucursalToShow = Sucursal.builder()
                    .id_sucursal(sucursal.getId_sucursal())
                    .nombre(sucursal.getNombre())
                    .telefono(sucursal.getTelefono())
                    .localidad(sucursal.getLocalidad())
                    .build();
            sucursalResponseList.add(sucursalResponse(sucursalToShow));
        });
        return sucursalResponseList;
    }
    private SucursalResponse sucursalResponse(Sucursal entity){
        var response = new SucursalResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
