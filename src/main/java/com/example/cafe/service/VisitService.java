package com.example.cafe.service;

import com.example.cafe.dto.VisitDTO;
import com.example.cafe.entity.Visit;
import com.example.cafe.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final ClientService clientService;
    public Visit create(VisitDTO dto){
        Visit visit = Visit.builder()
                .datetime(dto.getDatetime())
                .client(clientService.readById(dto.getClientId()))
                .build();
        return visitRepository.save(visit);
    }

    public List<Visit> readAll(){
        return visitRepository.findAll();
    }

    public void delete(Long id){
        visitRepository.deleteById(id);
    }

    public List<Visit> readByClientId(Long clientId){
        return visitRepository.findByClientId(clientId);
    }

}
