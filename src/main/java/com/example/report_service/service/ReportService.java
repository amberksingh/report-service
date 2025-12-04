package com.example.report_service.service;

import com.example.report_service.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class ReportService {

    int attemptById = 1;
    int attemptFindAll = 1;

    @Autowired
    @Qualifier("base_url_order_minikube")
    WebClient webClient;

    //Mono<ResponseEntity<Person>> entityMono = client.get()
    //      .uri("/persons/1")
    //      .accept(MediaType.APPLICATION_JSON)
    //      .retrieve()
    //      .toEntity(Person.class);
    //
    //Or if interested only in the body:
    //  Mono<Person> entityMono = client.get()
    //      .uri("/persons/1")
    //      .accept(MediaType.APPLICATION_JSON)
    //      .retrieve()
    //      .bodyToMono(Person.class);

    @Retryable(retryFor = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(value = 2000))
    public Order getOrder(Long orderId) {
        log.info("attemptById count : {}", attemptById);
        attemptById++;
//        Order block = webClient.get()
//                .uri("/order-table/findById/v2/" + orderId)
//                .retrieve()
//                .bodyToMono(Order.class)
//                .block();
        return webClient.get()
                .uri("/order-table/findById/v2/" + orderId)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
    }

    @Retryable(retryFor = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(value = 2000))
    public List<Order> findAllOrders() {
        //ParameterizedTypeReference<List<Order>> p = new ParameterizedTypeReference<>(){};
        log.info("attemptFindAll count : {}", attemptFindAll);
        attemptFindAll++;
//        List<Order> block = webClient.get()
//                .uri("/order-table/findAllOrders/v2")
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {
//                })
//                .block();
        return webClient.get()
                .uri("/order-table/findAllOrders/v2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {})
                .block();
    }

    @Recover
    public Order recoverGetOrder(RuntimeException e, Long orderId) {
        log.warn("inside recover for Order getOrder(Long orderId) method call {}", e.getMessage());
        log.error("Exception while calling order-service ", e);
        throw new RuntimeException("Runtime exception from order-service : ", e.getCause());
    }

    @Recover
    public List<Order> recoverFindAllOrders(RuntimeException e) {
        log.warn("inside recover for List<Order> findAllOrders() method call {}", e.getMessage());
        log.error("Exception while calling order-service ", e);
        throw new RuntimeException("Runtime exception from order-service : ", e.getCause());
    }

    @Recover
    public void recover(RuntimeException e) {
        System.out.println("Generic recover for unhandled retryable methods");
        System.out.println("Exception : " + e.getMessage());
    }

}
