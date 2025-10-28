package com.jei.applicacion.client;


import com.jei.applicacion.client.dto.ProyectoResponseDto;
import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "ms-proyectos",
        url = "http://localhost:8084/api/proyectos",
        configuration = FeignSecurityConfig.class
)
public interface ProyectoClient {
    @GetMapping
    List<ProyectoResponseDto> listarPorDepartamento(@RequestParam("departamento") String departamento);
}
