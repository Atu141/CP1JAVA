package com.github.Atu141.Cp1.service;


import com.github.Atu141.Cp1.dto.CidadeRequestDTO;
import com.github.Atu141.Cp1.dto.CidadeResponseDTO;
import com.github.Atu141.Cp1.entities.Cidade;
import com.github.Atu141.Cp1.entities.Evento;
import com.github.Atu141.Cp1.repositori.CidadeRepository;
import com.github.Atu141.Cp1.repositori.EventoRepository;
import com.github.Atu141.Cp1.service.execeptions.DatabaseException;
import com.github.Atu141.Cp1.service.execeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {


    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional(readOnly = true)
    public List<CidadeResponseDTO> findAll() {
        List<Cidade> list = repository.findAll();
        return list.stream().map(CidadeResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CidadeResponseDTO findById(Long id) {

        Cidade entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id: " + id)
        );
        return new CidadeResponseDTO(entity);
    }

    @Transactional
    public CidadeResponseDTO insert(CidadeRequestDTO requestDTO) {

        try {
            Cidade entity = new Cidade();
            // metodo auxiliar para converter DTO para Entity
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new CidadeResponseDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Violação de integridade referencial - evento ID: "
                    + requestDTO.evento().getId());
        }
    }

    @Transactional
    public CidadeResponseDTO update(Long id, CidadeRequestDTO requestDTO){

        try{
            Cidade entity = repository.getReferenceById(id);
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new CidadeResponseDTO(entity);
        } catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
        repository.deleteById(id);
    }

    private void toEntity(CidadeRequestDTO requestDTO, Cidade entity) {
        entity.setNome(requestDTO.nome());
        entity.setEstado(requestDTO.estado());
        entity.setUf(requestDTO.uf());

        // Objeto completo gerenciado
        Evento evento = EventoRepository.getReferenceById(requestDTO.evento().getId());
        entity.setEvento(evento);
    }
}
