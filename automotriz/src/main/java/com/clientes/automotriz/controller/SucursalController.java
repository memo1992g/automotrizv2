package com.clientes.automotriz.controller;

import com.clientes.automotriz.model.request.SucursalRequest;
import com.clientes.automotriz.model.response.SucursalResponse;
import com.clientes.automotriz.services.SucursalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("sucursal")
@CrossOrigin("http://localhost:4200")
public class SucursalController {
    private final SucursalService sucursalService;
    @GetMapping("")
    public ResponseEntity<List<SucursalResponse>> getAll(){
        return ResponseEntity.ok(sucursalService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<SucursalResponse> createSucursal(@Valid @RequestBody SucursalRequest request){
        return ResponseEntity.ok(sucursalService.createSucursal(request).get());
    }
}
