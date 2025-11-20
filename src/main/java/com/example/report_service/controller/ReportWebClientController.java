package com.example.report_service.controller;

import com.example.report_service.entity.Order;
import com.example.report_service.entity.OrderDTO;
import com.example.report_service.service.ReportService;
import com.example.report_service.utility.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportWebClientController {

    @Autowired
    WebClient webClient;

    @Autowired
    ReportService reportService;

    @GetMapping("/dummy")
    public String dummyReport() {
        log.info("Inside dummyReport...");
        return "Inside dummyReport...";
    }

    @GetMapping("/findById/{orderId}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Long orderId) {
        log.info("Finding order by id {}", orderId);
        Order order = reportService.getOrder(orderId);//call to service layer
        log.info("order : {}", order);
        OrderDTO dto = OrderMapper.toDTO(order);
        log.info("OrderDTO : {}", dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findById/v2/{orderId}")
    public ResponseEntity<String> findOrderByIdNonBlocking(@PathVariable Long orderId) {
        log.info("findOrderById-NonBlocking order by id {}", orderId);
        webClient.get()
                .uri("/order-table/findById/v2/" + orderId)
                .retrieve()
                .bodyToMono(Order.class)
                .subscribe(o -> {
                    log.info("received response from order-service : {}", o);
                });
        return ResponseEntity.ok("findOrderByIdNonBlocking request sent to order-service");
    }

    @GetMapping("/findAllOrders")
    public ResponseEntity<List<OrderDTO>> findAllOrders() {
        log.info("Finding all orders ");
        List<Order> orders = reportService.findAllOrders();//call to service layer
        log.info("orders : {}", orders);
        List<OrderDTO> dtoList = OrderMapper.toDTO(orders);
        log.info("OrderDTO list : {}", dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/findAllOrders/v2")
    public ResponseEntity<String> findAllOrdersNonBlocking() {
        log.info("Finding all orders NonBlocking..");
        webClient.get()
                .uri("/order-table/findAllOrders/v2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {
                })
                .subscribe(orders -> {
                    log.info("received orders responses from order-service : ");
                    orders.forEach(o ->
                            log.info("order : " + o)
                    );
                });

        return ResponseEntity.ok("findAllOrdersNonBlocking request sent to order-service");
    }

    @GetMapping("/findAllOrders/v3")
    public Mono<List<OrderDTO>> findAllOrdersNonBlockingMap() {
        log.info("Finding all orders NonBlocking map..");
        return webClient.get()
                .uri("/order-table/findAllOrders/v2")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Order>>() {
                })
                .map(orders -> {
                    log.info("received orders responses from order-service map : {}", orders);
                    return OrderMapper.toDTO(orders);
                });

        //return ResponseEntity.ok("findAllOrdersNonBlocking request sent to order-service");
    }

}


//✅ Why Flyway Failed? — Root Cause
//
//Flyway compares two things for every migration:
//
//Migration	Meaning
//Applied to database	The hash (checksum) of the SQL file when it first ran
//Resolved locally	The hash of the SQL file currently in your code
//
//You changed your V2__insert_mock_reports.sql file (you updated timestamps).
//
//So now:
//
//Applied to database : 1685343880
//Resolved locally    : 266604632
//
//
//Meaning:
//
//✔ Database has old version
//✘ Code has new version
//→ CHECKSUM MISMATCH → Flyway blocks startup
//
//This is correct Flyway behavior (to prevent accidental data corruption).
//
//🛑 Why did dropping ONLY the table NOT fix it?
//
//Dropping reports table is not enough.
//
//Because Flyway history lives in:
//
//flyway_schema_history
//
//
//You did NOT drop that — so Flyway still thinks V2 was already applied.
//
//✅ 3 Ways to Fix This (Choose ONE)
//OPTION 1 — Best for Local Dev (Quick fix)
//
//Delete the Flyway history table so Flyway can run migrations fresh.
//
//Run this in MySQL:
//DROP TABLE flyway_schema_history;
//
//
//Then restart Spring Boot.
//
//→ Flyway will re-run V1 + V2 cleanly.
//→ No mismatch.
//
//OPTION 2 — Flyway Repair (Official way)
//
//Run the Flyway repair command:
//
//Terminal
//mvn flyway:repair
//
//
//or if Spring Boot has Flyway CLI:
//
//flyway repair
//
//
//This updates the checksum in DB to match new file.
//
//OPTION 3 — NEVER EDIT old migration files
//
//This is the correct rule for production:
//
//❌ Never edit V1, V2, V3…
//✔ Always create new file:
//
//V3__fix_dates.sql
//
//
//But since you are local and learning, you can edit V2 — just repair or drop history.
//
//⚠️ About the Date Error You Saw Earlier
//
//Earlier you had:
//
//Incorrect date value: '2025-11-10 16:26:10'
//
//
//Because your column type was:
//
//order_date TIMESTAMP
//
//
//But your insert tried to insert:
//
//'2025-11-10 16:26:10'
//
//
//Which is valid only if column type is TIMESTAMP, which you fixed.
//
//Now inserts are correct.
//
//🔥 Final Recommended Fix for You (Fastest)
//1. Drop old Flyway history
//DROP TABLE flyway_schema_history;
//
//2. Drop old reports table
//DROP TABLE reports;
//
//3. Restart app
//
//→ Flyway runs V1 → creates table
//→ Flyway runs V2 → inserts 20 rows
//→ No checksum mismatch.
