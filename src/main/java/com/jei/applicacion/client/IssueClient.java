package com.jei.applicacion.client;

import com.jei.applicacion.client.dto.IssueResponseDto;
import com.jei.config.FeignSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "ms-issues",
        url = "http://localhost:8082/api/issues",
        configuration = FeignSecurityConfig.class
)
public interface IssueClient {
    @GetMapping
    List<IssueResponseDto> listarTodos();
}
