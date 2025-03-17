package com.github.Atu141.Cp1.dto;

import com.github.Atu141.Cp1.entities.Cidade;

public record EventoResponseDTO(
        Long id,
        String nome,
        String estado,
        String uf,
        CidadeDTO evento
) {

    public EventoResponseDTO(Cidade entity) {
        this(entity.getId(),
                entity.getNome(),
                entity.getEstado(),
                entity.getUf(),
                new CidadeDTO(entity.getEvento()));
    }
}
