package com.rodrigo.hrworker.resources;

import com.rodrigo.hrworker.entities.Worker;
import com.rodrigo.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    //@Value("${test.com.rodrigo.hrapigatewayzuul.config}")
    //private String testConfig;

    @Autowired
    private Environment environment;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/configs")
    public ResponseEntity<Void> getConfigs() {
     //   logger.info("CONFIG CLOUD: " + testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok().body(workerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {

        // Teste de tolerância a falha com o Hystrix
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        logger.info("PORTA = " + environment.getProperty("local.server.port"));

        return workerRepository.findById(id)
                .map(w -> ResponseEntity.ok().body(w))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
