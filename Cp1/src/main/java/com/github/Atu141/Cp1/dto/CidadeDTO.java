package com.github.Atu141.Cp1.dto;

import com.github.Atu141.Cp1.entities.Cidade;
import com.github.Atu141.Cp1.entities.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CidadeDTO {

    private Long id;

    @NotBlank(message = "O nome não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O nome não pode ser vazio, nulo ou em branco")
    @Size(message = "O formato da data é ano/mes/dia")
    private String estado;

    @NotBlank(message = "Digite somente a sigla")
    @Size( max = 2, message = "O nome deve ter somente dois caracteres")
    private String uf;

    public CidadeDTO(Cidade entity) {
        id = entity.getId();
        nome = entity.getNome();
        estado = entity.getEstado();
        uf = entity.getUf();
    }

}
