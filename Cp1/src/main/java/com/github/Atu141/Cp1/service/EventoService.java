package com.github.Atu141.Cp1.service;

import com.github.Atu141.Cp1.dto.EventoDTO;
import com.github.Atu141.Cp1.entities.Evento;
import com.github.Atu141.Cp1.repositori.EventoRepository;
import com.github.Atu141.Cp1.service.execeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    @Transactional(readOnly = true)
    public List<EventoDTO>findAll(){
        return repository.findAll().stream().map(EventoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public EventoDTO findById(Long id){
        Evento entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id" + id)
        );
        return new EventoDTO(entity);
    }

    @Transactional
    public EventoDTO create(EventoDTO dto){
        Evento entity = new Evento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EventoDTO(entity);
    }

    private void copyDtoToEntity(EventoDTO dto, Evento entity){
        entity.setNome(dto.getNome());
    }

    @Transactional
    public EventoDTO update(Long id, EventoDTO dto){
        try{
            Evento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventoDTO(entity);
        }catch (EntityNotFoundException ex ){
            throw new ResourceNotFoundException("Recuso não encontrado. ID" + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Recuso não encontrado. ID" + id);
        }
        repository.deleteById(id);
    }

}
