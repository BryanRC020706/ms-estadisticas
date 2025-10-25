package com.jei.applicacion.service.impl;

import com.jei.applicacion.client.EpicaClient;
import com.jei.applicacion.client.IssueClient;
import com.jei.applicacion.client.ProyectoClient;
import com.jei.applicacion.client.UsuarioClient; // cambiado
import com.jei.applicacion.client.dto.IssueResponseDto;
import com.jei.applicacion.service.EstadisticaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();

        var proyectos = proyectoClient.listarTodos();
        var epicas = epicaClient.listarTodos();
        var issues = issueClient.listarTodos();
        var usuarios = usuarioClient.listarTodos();

        Map<String, Long> issuesPorEstado = issues.stream()
                .collect(Collectors.groupingBy(
                        IssueResponseDto::getEstado,
                        Collectors.counting()
                ));

        Map<String, Long> issuesPorPrioridad = issues.stream()
                .collect(Collectors.groupingBy(
                        IssueResponseDto::getPrioridad,
                        Collectors.counting()
                ));

        estadisticas.put("totalProyectos", proyectos.size());
        estadisticas.put("totalEpicas", epicas.size());
        estadisticas.put("totalIssues", issues.size());
        estadisticas.put("totalUsuarios", usuarios.size());
        estadisticas.put("issuesPorEstado", issuesPorEstado);
        estadisticas.put("issuesPorPrioridad", issuesPorPrioridad);

        return estadisticas;
    }
}
