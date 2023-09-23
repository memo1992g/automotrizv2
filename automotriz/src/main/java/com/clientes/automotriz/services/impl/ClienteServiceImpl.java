package com.clientes.automotriz.services.impl;

import com.clientes.automotriz.entity.Cliente;
import com.clientes.automotriz.exception.IdClienteNotFound;
import com.clientes.automotriz.model.request.ClienteRequest;
import com.clientes.automotriz.model.request.ClienteUpdateRequest;
import com.clientes.automotriz.model.response.ClienteResponse;
import com.clientes.automotriz.repository.ClienteRepository;
import com.clientes.automotriz.repository.SucursalRepository;
import com.clientes.automotriz.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
	
	
    private final ClienteRepository clienteRepository;
    private final SucursalRepository sucursalRepository;
    
   
    
    
    @Override
    public Optional<ClienteResponse> createCliente(ClienteRequest request) {
        var sucursal = sucursalRepository.findById(request.getIdSucursal()).get();
        SimpleDateFormat dataBaseFormat = new SimpleDateFormat("yyyy-MM-dd");
        log.info("********** Obteniendo cliente request {} **********", request);
        try {
            var clienteToSave = Cliente.builder()
                    .estado(1)
                    .correo(request.getCorreo())
                    .fechaNacimiento(dataBaseFormat.parse(dataBaseFormat.format(request.getFechaNacimiento())))
                    .direccion(request.getDireccion())
                    .primerNombre(request.getPrimerNombre())
                    .segundoNombre(request.getSegundoNombre())
                    .primerApellido(request.getPrimerApellido())
                    .segundoApellido(request.getSegundoApellido())
                    .sucursal(sucursal)
                    .telefono(request.getTelefono())
                    .build();
            var clientSaved = clienteRepository.save(clienteToSave);
            log.info("********** Cliente creado {} **********", clienteResponse(clientSaved));
            return Optional.ofNullable(clienteResponse(clientSaved));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClienteResponse> getAll(String localidad) {
        log.info("***** Obteniendo valor localidad {} *****", localidad);
        List<ClienteResponse> clienteResponseList = new ArrayList<>();
        List<Cliente> clientesToGet;
        if (Objects.isNull(localidad) || localidad.isEmpty()){
            clientesToGet = clienteRepository.getAllActiveCustomer();
        }else {
            clientesToGet = clienteRepository.findBySucursal_Localidad(localidad);
        }
        for (Cliente cliente: clientesToGet){
            var clienteToShow = ClienteResponse.builder()
                    .idCliente(cliente.getIdCliente())
                    .direccion(cliente.getDireccion())
                    .fechaNacimiento(cliente.getFechaNacimiento())
                    .sucursal(cliente.getSucursal().getNombre())
                    .idSucursal(cliente.getSucursal().getId_sucursal())
                    .primerNombre(cliente.getPrimerNombre())
                    .segundoNombre(cliente.getSegundoNombre())
                    .primerApellido(cliente.getPrimerApellido())
                    .segundoApellido(cliente.getSegundoApellido())
                    .telefono(cliente.getTelefono())
                    .correo(cliente.getCorreo())
                    .build();
            clienteResponseList.add(clienteToShow);
        }
        return clienteResponseList;
    }

    @Override
    public Optional<ClienteResponse> getClienteById(Integer id) {
        var clienteToGet = clienteRepository.findById(id).orElseThrow(()-> new IdClienteNotFound());
        var clienteToShow = clienteResponse(clienteToGet);
        return Optional.of(clienteToShow);
    }

    @Override
    public Optional<ClienteResponse> updateCliente(ClienteUpdateRequest request, Integer id) {
        SimpleDateFormat dataBaseFormat = new SimpleDateFormat("yyyy-MM-dd");
        var clienteToUpdate = clienteRepository.findById(id).orElseThrow(()-> new IdClienteNotFound());
        if (request.getCamposActualizados().containsKey("correo"))
            clienteToUpdate.setCorreo(request.getCamposActualizados().get("correo").toString());
        if (request.getCamposActualizados().containsKey("primerNombre"))
            clienteToUpdate.setPrimerNombre(request.getCamposActualizados().get("primerNombre").toString());
        if (request.getCamposActualizados().containsKey("segundoNombre"))
            clienteToUpdate.setSegundoNombre(request.getCamposActualizados().get("segundoNombre").toString());
        if (request.getCamposActualizados().containsKey("primerApellido"))
            clienteToUpdate.setPrimerApellido(request.getCamposActualizados().get("primerApellido").toString());
        if (request.getCamposActualizados().containsKey("segundoApellido"))
            clienteToUpdate.setSegundoApellido(request.getCamposActualizados().get("segundoApellido").toString());
        if (request.getCamposActualizados().containsKey("telefono"))
            clienteToUpdate.setTelefono(request.getCamposActualizados().get("telefono").toString());
        if (request.getCamposActualizados().containsKey("direccion"))
            clienteToUpdate.setDireccion(request.getCamposActualizados().get("direccion").toString());
        if (request.getCamposActualizados().containsKey("fechaNacimiento")) {
            try {
         //       SimpleDateFormat fechaEsperada = new SimpleDateFormat("yyyy-MM-dd");
                clienteToUpdate.setFechaNacimiento(dataBaseFormat
                        .parse(request.getCamposActualizados().get("fechaNacimiento").toString()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        if (request.getCamposActualizados().containsKey("idSucursal")) {
            var sucursalUpdated = sucursalRepository.findById(Integer.parseInt(request.getCamposActualizados().get("idSucursal").toString())).get();
            clienteToUpdate.setSucursal(sucursalUpdated);
        }
        var clienteUpdated = clienteRepository.save(clienteToUpdate);
        return Optional.of(clienteResponse(clienteUpdated));
    }

    @Override
    public Optional<ClienteResponse> deleteCliente(Integer idCliente) {
        var clienteToDelete = clienteRepository.findById(idCliente).orElseThrow(()-> new IdClienteNotFound());
        clienteToDelete.setEstado(0);
        var clienteDeleted = clienteRepository.save(clienteToDelete);
        return Optional.of(clienteResponse(clienteDeleted));
    }
    private ClienteResponse clienteResponse(Cliente entity){
        var response = new ClienteResponse();
        BeanUtils.copyProperties(entity, response);
        response.setSucursal(entity.getSucursal().getNombre());
        response.setIdSucursal(entity.getSucursal().getId_sucursal());
        return response;
    }

}
