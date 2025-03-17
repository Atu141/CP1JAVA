package com.github.Atu141.Cp1.service;


import com.github.Atu141.Cp1.dto.EventoRequestDTO;
import com.github.Atu141.Cp1.dto.EventoResponseDTO;
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
public class EventoService {


    @Autowired
    private EventoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public List<EventoResponseDTO> findAll() {
        List<Evento> list = repository.findAll();
        return list.stream().map(EventoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public EventoResponseDTO findById(Long id) {

        Evento entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id: " + id)
        );
        return new EventoResponseDTO(entity);
    }

    @Transactional
    public EventoResponseDTO insert(EventoRequestDTO requestDTO) {

        try {
            Evento entity = new Evento();
            // metodo auxiliar para converter DTO para Entity
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new EventoResponseDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Violação de integridade referencial - evento ID: "
                    + requestDTO.cidade().getId());
        }
    }

    @Transactional
    public EventoResponseDTO update(Long id, EventoRequestDTO requestDTO){

        try{
            Evento entity = repository.getReferenceById(id);
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new EventoResponseDTO(entity);
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

    private void toEntity(EventoRequestDTO requestDTO, Evento entity) {
        entity.setNome(requestDTO.nome());
        entity.setData(requestDTO.data());
        entity.setUrl(requestDTO.url());

        // Objeto completo gerenciado
        Cidade cidade = CidadeRepository.getReferenceById(requestDTO.cidade().getId());
        entity.setCidade(cidade);
    }
}
