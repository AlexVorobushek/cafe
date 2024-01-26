package com.example.cafe.controller;

import com.example.cafe.dto.ClientDTO;
import com.example.cafe.entity.Client;
import com.example.cafe.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO dto){
        dto.checkIsValid();

        return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client client){
        client.checkIsValid();
        clientService.checkExistById(client.getId());

        return new ResponseEntity<>(clientService.update(client), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        clientService.checkExistById(id);

        clientService.delete(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> readAll(){
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
