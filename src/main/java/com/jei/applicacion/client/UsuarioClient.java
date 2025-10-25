package com.jei.applicacion.client;

import com.jei.applicacion.client.dto.UsuarioResponseDto;
import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "usuario-service",
        url = "http://localhost:8085/api/usuario",
        configuration = FeignSecurityConfig.class
)

public interface UsuarioClient {
    @GetMapping
    List<UsuarioResponseDto> listarTodos();
}
