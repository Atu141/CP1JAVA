package com.github.Atu141.Cp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record EventoRequestDTO(
        @NotBlank(message = "Campo Requerido")
        @Size(min = 3, max = 100, message = "O Nome deve ter entre 3 a 100 caracteres")
        String nome,

        @NotBlank(message = "Campo Requerido")
        @Size(min = 3, max = 100, message = "O Estado deve ter entre 3 a 100 caracteres")
        LocalDate data,

        @NotBlank(message = "Campo Requerido")
        @Size(max = 2, message = "O Uf deve ter exatamente 2 caracteres")
        String url,
        @NotNull(message = "Campo requerido")
        CidadeDTO cidade) {


}
