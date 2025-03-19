package com.github.Atu141.Cp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record   EventoRequestDTO(
        @NotBlank(message = "Campo Requerido")
        @Size(min = 3, max = 100, message = "O Nome deve ter entre 3 a 100 caracteres")
        String nome,

        @NotNull(message = "Campo Requerido")
        @Size(message = " formato da data deve ser 'yyyy-mm-dd' ")
        LocalDate data,

        @NotBlank(message = "Campo Requerido")
        @Size( min=10, max = 255, message = "O Url deve ter no minimo 10 caracteres")
        String url,

        @NotNull(message = "Campo requerido")
        CidadeDTO cidade) {


}
