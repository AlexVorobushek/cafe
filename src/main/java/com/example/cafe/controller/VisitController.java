package com.example.cafe.controller;

import com.example.cafe.dto.VisitDTO;
import com.example.cafe.entity.Visit;
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

    @PostMapping
    public ResponseEntity<Visit> create(@RequestBody VisitDTO dto){
        try {
            return new ResponseEntity<>(visitService.create(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<List<Visit>> readAll(){
        return new ResponseEntity<>(visitService.readAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        visitService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/client{id}")
    public ResponseEntity<List<Visit>> readByClient(@PathVariable Long id){
        return new ResponseEntity<>(visitService.readByClientId(id), HttpStatus.OK);
    }
}
