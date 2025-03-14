package com.github.Atu141.Cp1.dto;

import com.github.Atu141.Cp1.entities.Cidade;

public record CidadeResponseDTO(
        Long id,
        String nome,
        String estado,
        String uf,
        EventoDTO evento
) {

    public CidadeResponseDTO(Cidade entity) {
        this(entity.getId(),
                entity.getNome(),
                entity.getEstado(),
                entity.getUf(),
                new EventoDTO(entity.getEvento()));
    }
}
