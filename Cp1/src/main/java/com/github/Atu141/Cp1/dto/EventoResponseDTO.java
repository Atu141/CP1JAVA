package com.github.Atu141.Cp1.dto;

import com.github.Atu141.Cp1.entities.Cidade;
import com.github.Atu141.Cp1.entities.Evento;

import java.time.LocalDate;

public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDate data,
        String uf,
        CidadeDTO cidade
) {

    public EventoResponseDTO(Evento entity) {
        this(entity.getId(),
                entity.getNome(),
                entity.getData(),
                entity.getUrl(),
                new CidadeDTO(entity.getCidade()));
    }
}
