package com.jei.applicacion.service;

import com.jei.applicacion.client.dto.Departamento;

import java.util.Map;

public interface EstadisticaService {
    Map<String, Object> obtenerEstadisticas(String departamento);
}
