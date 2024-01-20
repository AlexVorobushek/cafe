package com.example.cafe.service;

import com.example.cafe.dto.ClientDTO;
import com.example.cafe.entity.Client;
import com.example.cafe.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client create(ClientDTO dto){
        Client client = Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .build();
        return clientRepository.save(client);
    }

    public List<Client> readAll(){
        return clientRepository.findAll();
    }

    public Client update(Client client){
        return clientRepository.save(client);
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    public Client readById(Long id){
        return clientRepository.findById(id).orElseThrow(()->
                new RuntimeException("there are no client with "+id+" id"));
    }
}
