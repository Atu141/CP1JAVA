package com.github.Atu141.Cp1.service;

import com.github.Atu141.Cp1.dto.CidadeDTO;
import com.github.Atu141.Cp1.entities.Cidade;
import com.github.Atu141.Cp1.repository.CidadeRepository;
import com.github.Atu141.Cp1.service.execeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Transactional(readOnly = true)
    public List<CidadeDTO>findAll(){
        return repository.findAll().stream().map(CidadeDTO::new).toList();

    }

    @Transactional(readOnly = true)
    public CidadeDTO findById(Long id){
        Cidade entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id" + id)
        );
        return new CidadeDTO(entity);
    }

    @Transactional
    public CidadeDTO create(CidadeDTO dto){
        Cidade entity = new Cidade();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CidadeDTO(entity);
    }

    private void copyDtoToEntity(CidadeDTO dto, Cidade entity){
        entity.setNome(dto.getNome());
    }

    @Transactional
    public CidadeDTO update(Long id, CidadeDTO dto){
        try{
            Cidade entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CidadeDTO(entity);
        }catch (EntityNotFoundException ex ){
            throw new ResourceNotFoundException("Recurso não encontrado. ID" + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID" + id);
        }
        repository.deleteById(id);
    }

}
