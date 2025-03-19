package com.github.Atu141.Cp1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_evento")
public class Evento {

    @Id
    private long id;
    private String nome;
    private LocalDate data;
    private String url;



    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Cidade cidade;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
