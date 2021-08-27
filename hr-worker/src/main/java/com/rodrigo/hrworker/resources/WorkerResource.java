package com.rodrigo.hrworker.resources;

import com.rodrigo.hrworker.entities.Worker;
import com.rodrigo.hrworker.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    private ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok().body(workerRepository.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Worker> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(workerRepository.findById(id).get());
    }
}
