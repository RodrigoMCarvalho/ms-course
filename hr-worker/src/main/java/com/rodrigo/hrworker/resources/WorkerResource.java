package com.rodrigo.hrworker.resources;

import com.rodrigo.hrworker.entities.Worker;
import com.rodrigo.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    @Autowired
    private Environment environment;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok().body(workerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        logger.info("PORTA = " + environment.getProperty("local.server.port"));

        return workerRepository.findById(id)
                .map(w -> ResponseEntity.ok().body(w))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
