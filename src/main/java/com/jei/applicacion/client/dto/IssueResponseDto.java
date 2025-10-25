package com.jei.applicacion.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResponseDto {
    private Long id;
    private String estado;
    private String prioridad;
}
