package com.example.cafe.controller;

import com.example.cafe.dto.VisitDTO;
import com.example.cafe.entity.Visit;
import com.example.cafe.service.ClientService;
import com.example.cafe.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
@AllArgsConstructor
public class VisitController {
    private final VisitService visitService;
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody VisitDTO dto){
        dto.checkIsDTOValid();
        clientService.checkExistById(dto.getClientId());

        visitService.create(dto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Visit>> readAll(){
        return new ResponseEntity<>(visitService.readAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        visitService.checkExistById(id);

        visitService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/client{id}")
    public ResponseEntity<List<Visit>> readByClient(@PathVariable Long id){
        clientService.checkExistById(id);

        return new ResponseEntity<>(visitService.readByClientId(id), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
