package com.rodrigo.hrpayroll.services;

import com.rodrigo.hrpayroll.entities.Payment;
import com.rodrigo.hrpayroll.entities.Worker;
import com.rodrigo.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    private WorkerFeignClient workerFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(long workId, int days) {
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id", Long.toString(workId));
//
//        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);

        Worker worker = workerFeignClient.findById(workId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
