package com.github.Atu141.Cp1.controller;

import com.github.Atu141.Cp1.dto.CidadeRequestDTO;
import com.github.Atu141.Cp1.dto.CidadeResponseDTO;
import com.github.Atu141.Cp1.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseEntity<List<CidadeResponseDTO>> findAll() {

        List<CidadeResponseDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> findById(@PathVariable Long id) {

        CidadeResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CidadeResponseDTO> insert(@Valid @RequestBody CidadeRequestDTO requestDTO) {

        CidadeResponseDTO dto = service.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.id())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody CidadeRequestDTO requestDTO) {

        CidadeResponseDTO dto = service.update(id, requestDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

