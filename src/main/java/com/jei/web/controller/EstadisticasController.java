package com.jei.web.controller;

import com.jei.applicacion.client.dto.Departamento;
import com.jei.applicacion.service.EstadisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {
    private final EstadisticaService estadisticaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerEstadisticas(@RequestParam(value = "departamento", required = false) String departamentoStr) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userRole = "ADMIN";
        String userDepartamento = "COMERCIAL";

        if (auth != null && auth.getPrincipal() != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof Jwt jwt) {
                userRole = jwt.getClaimAsString("role");
                userDepartamento = jwt.getClaimAsString("departamento");
            }
        }

        String departamentoFinal;
        if (departamentoStr != null && "ADMIN".equalsIgnoreCase(userRole)) {
            departamentoFinal = departamentoStr.toUpperCase();
        } else {
            departamentoFinal = userDepartamento.toUpperCase();
        }

        Map<String, Object> response = estadisticaService.obtenerEstadisticas(departamentoFinal);
        return ResponseEntity.ok(response);
    }
}
