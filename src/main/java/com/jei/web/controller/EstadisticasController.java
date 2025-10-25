package com.jei.web.controller;

import com.jei.applicacion.service.EstadisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {
    private final EstadisticaService estadisticaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas() {
        Map<String, Object> response = estadisticaService.obtenerEstadisticas();
        return ResponseEntity.ok(response);
    }
}
