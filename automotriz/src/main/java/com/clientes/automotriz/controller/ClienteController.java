package com.clientes.automotriz.controller;

import com.clientes.automotriz.model.request.ClienteRequest;
import com.clientes.automotriz.model.request.ClienteUpdateRequest;
import com.clientes.automotriz.model.response.ClienteResponse;
import com.clientes.automotriz.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("cliente")
public class ClienteController {
    private final ClienteService clienteService;
    
    
    @GetMapping("")
    public ResponseEntity<List<ClienteResponse>> getAll(@RequestParam(required = false) String localidad){
        return ResponseEntity.ok(clienteService.getAll(localidad));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getAll(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.getClienteById(id).get());
    }
    @Operation(summary = "Formato para campo fechaNacimiento es el siguiente: dd/MM/yyyy")
    @PostMapping("")
    public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest request){
        return ResponseEntity.ok(clienteService.createCliente(request).get());
    }
    @Operation(summary = "Formato para campo fechaNacimiento es el siguiente: dd/MM/yyyy")
    @PatchMapping("{id}")
    public ResponseEntity<ClienteResponse> updateCliente(@PathVariable Integer id, @RequestBody ClienteUpdateRequest request){
        return ResponseEntity.ok(clienteService.updateCliente(request, id).get());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ClienteResponse> deleteCliente(@PathVariable Integer id){
        return ResponseEntity.ok(clienteService.deleteCliente(id).get());
    }
}
