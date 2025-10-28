package com.jei.applicacion.client;

import com.jei.applicacion.client.dto.EpicaResponseDto;
import com.jei.applicacion.client.dto.ProyectoResponseDto;
import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "ms-epicas",
        url = "http://localhost:8083/api/epicas",
        configuration = FeignSecurityConfig.class
)
public interface EpicaClient {
    @GetMapping
    List<EpicaResponseDto> listarPorDepartamento(@RequestParam("departamento") String departamento);
}
