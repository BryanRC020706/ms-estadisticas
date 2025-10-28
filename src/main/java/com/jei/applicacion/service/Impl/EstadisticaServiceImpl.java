package com.jei.applicacion.service.Impl;

import com.jei.applicacion.client.EpicaClient;
import com.jei.applicacion.client.IssueClient;
import com.jei.applicacion.client.ProyectoClient;
import com.jei.applicacion.client.UsuarioClient; // cambiado
import com.jei.applicacion.client.dto.Departamento;
import com.jei.applicacion.client.dto.Estado;
import com.jei.applicacion.client.dto.IssueResponseDto;
import com.jei.applicacion.client.dto.Prioridad;
import com.jei.applicacion.service.EstadisticaService;
import lombok.RequiredArgsConstructor;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService {

    private final ProyectoClient proyectoClient;
    private final EpicaClient epicaClient;
    private final IssueClient issueClient;
    private final UsuarioClient usuarioClient;

    @Override
    public Map<String, Object> obtenerEstadisticas(String departamento) {
        Map<String, Object> estadisticas = new HashMap<>();



        var proyectos = proyectoClient.listarPorDepartamento(departamento);
        var epicas = epicaClient.listarPorDepartamento(departamento);
        var issues = issueClient.listarPorDepartamento(departamento);
        var usuarios = usuarioClient.listarPorDepartamento(departamento);

        Map<String, Long> issuesPorEstado = Arrays.stream(Estado.values())
                .collect(Collectors.toMap(
                        Enum::name,
                        e -> 0L,
                        (a, b) -> b,
                        LinkedHashMap::new
                ));
        issues.forEach(issue ->
                issuesPorEstado.merge(issue.getEstado(), 1L, Long::sum)
        );

        Map<String, Long> issuesPorPrioridad = Arrays.stream(Prioridad.values())
                .collect(Collectors.toMap(
                        Enum::name,
                        p -> 0L,
                        (a, b) -> b,
                        LinkedHashMap::new
                ));
        issues.forEach(issue ->
                issuesPorPrioridad.merge(issue.getPrioridad(), 1L, Long::sum)
        );

        estadisticas.put("totalProyectos", proyectos.size());
        estadisticas.put("totalEpicas", epicas.size());
        estadisticas.put("totalIssues", issues.size());
        estadisticas.put("totalUsuarios", usuarios.size());
        estadisticas.put("issuesPorEstado", issuesPorEstado);
        estadisticas.put("issuesPorPrioridad", issuesPorPrioridad);

        return estadisticas;
    }
}
