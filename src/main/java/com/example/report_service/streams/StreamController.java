package com.example.report_service.streams;

import com.example.report_service.ReportJpaRepo;
import com.example.report_service.entity.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/stream")
public class StreamController {

    @Autowired
    ReportJpaRepo jpaRepo;

    // 2️⃣ Run any stream question by passing the number
    @GetMapping("/{problemNumber}")
    public Object executeProblem(@PathVariable int problemNumber) {

        List<Report> reports = jpaRepo.findAll();

        switch (problemNumber) {

            case 1:
                //P1 – Distinct Product Names (case-sensitive)
                //Objective: Return a distinct, sorted list of all productName values.
                //Result type: Result type: List.
                //Expected output (conceptual): Expected: 20+ unique names like Laptop Pro, Organic Rice, Winter
                //Jacket, etc.
                //Hint: Hint: map -> distinct -> sorted -> toList.
                List<String> uniqueProdNames = reports.stream()
                        .map(Report::getProductName)
                        .distinct()
                        .toList();
                log.info("list of products : {}", uniqueProdNames);
                return uniqueProdNames;

            case 2:
                //Total Amount Spent by Each User (groupingBy)
                //Problem: Group by userId → sum(amount). Expected (sample of first few):
                //
                //10 → 1310.50
                //11 → 1335.00
                //23 → 81.99
                //30 → 905.00
                //(You must return all users). Hint: Collectors.groupingBy(... summingDouble(...))
                Map<Long, Double> collect = reports.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Report::getUserId,
                                        Collectors.summingDouble(Report::getAmount)
                                )
                        );
                log.info("groupingBy : {}", collect);
                return collect;

            case 3:
                //🟩 3️⃣ Highest Rated Product Per Category (maxBy)
                //Problem: For each category, return the productName with the highest rating. Expected:
                //
                //Electronics → Gaming Console (rating 5)
                //Grocery → Eggs Pack (rating 5)
                //Fashion → Jacket (rating 5)
                //Hint: groupBy(category → maxBy rating).
//                Map<String, String> collect1 = reports.stream()
//                        .collect(
//                                Collectors.groupingBy(
//                                        Report::getCategory,
//                                        Collectors.collectingAndThen(
//                                                Collectors.maxBy(Comparator.comparingInt(Report::getRating)),
//                                                opt -> opt.map(Report::getProductName).orElse(null)
//                                        )
//                                )
//                        );
//                log.info("problem 3 : {}", collect1);
//                return collect1;
                // Highest Rated Products Per Category (TIE SAFE)

                Map<String, List<String>> highestRatedByCategory =
                        reports.stream()
                                .collect(Collectors.groupingBy(
                                        Report::getCategory,
                                        Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                listInCategory -> {
                                                    int maxRating = listInCategory.stream()
                                                            .mapToInt(Report::getRating)
                                                            .max()
                                                            .orElse(0);

                                                    return listInCategory.stream()
                                                            .filter(r -> r.getRating() == maxRating)
                                                            .map(Report::getProductName)
                                                            .distinct()
                                                            .toList();
                                                }
                                        )
                                ));

                log.info("Problem 3 (tie safe): {}", highestRatedByCategory);
                return highestRatedByCategory;


            case 4: //Refunded vs Non-Refunded Count
                //Problem: Count how many orders were refunded or not. Expected:
                //refunded: 14
                //not_refunded: 26
                Map<Boolean, Long> collect2 = reports.stream()
                        .collect(
                                Collectors.partitioningBy(
                                        report -> report.isRefunded() == true,
                                        Collectors.counting()
                                )
                        );
                log.info("refunded/non-refunded : {}", collect2);
                Map<String, Long> map = new HashMap<>();
                map.put("refunded", collect2.get(true));
                map.put("non-refunded", collect2.get(false));
                Set<Map.Entry<String, Long>> entries = map.entrySet();
                for (Map.Entry<String, Long> e : entries) {
                    log.info("{} -> {}", e.getKey(), e.getValue());
                }
                return entries;

            case 5:
                //Users with More Than 2 Orders (filter + grouping)
                //Problem: Return list of userIds with >2 orders. Expected:
                //
                //[10, 11, 23, 30, 31, 32, 33, 34, 37, 38]
                //Hint: groupBy size >2.
                List<Long> list = reports.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Report::getUserId,
                                        Collectors.counting()
                                )
                        )
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() > 2)
                        .map(Map.Entry::getKey)
                        .toList();
                log.info("userIds with more than 2 orders -> {}", list);
                return list;

            case 6:
                //Maximum Delivery Days Order
                //Problem: Return the record with max deliveryDays. Expected:
                //
                //orderId: 105 → deliveryDays: 10
                //orderId: 113 → deliveryDays: 10
                //orderId: 136 → deliveryDays: 10
                //Hint: max(Comparator.comparingInt(getDeliveryDays)).
//                ToIntFunction<Report> toIntFunction = Report::getDeliveryDays;
//                Report report = reports.stream()
//                        .max(Comparator.comparingInt(toIntFunction))
//                        .orElse(null);
//                log.info("report with max deliveryDays -> {}", report);
//                return report;
                // Orders with maximum delivery days (TIE SAFE)

                int maxDays = reports.stream()
                        .mapToInt(Report::getDeliveryDays)
                        .max()
                        .orElse(0);

                List<Report> longestDeliveryOrders = reports.stream()
                        .filter(r -> r.getDeliveryDays() == maxDays)
                        .toList();

                log.info("Problem 6 (tie safe): {}", longestDeliveryOrders);
                return longestDeliveryOrders;

            case 7:
                //Region → Set of Unique Payment Methods (groupingBy + toSet)
                //Problem: For each region return Set(paymentMethod). Expected (example):
                //
                //North → [UPI, CARD, STORE, COD]
                //South → [UPI, COD, CARD]
                //East →  [CARD, UPI]
                //West →  [UPI, CARD]
                //Hint: Collectors.toSet().
                Map<String, Set<String>> collect1 = reports.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Report::getRegion,
                                        Collectors.mapping(
                                                Report::getPaymentMethod,
                                                Collectors.toSet()
                                        )
                                )
                        );
                collect1.forEach((k, v) -> log.info("{} -> {}", k, v));
                return collect1;

            case 8:
                // Sorted by Rating Desc, then Amount Desc
                //Problem: Sort records by rating ↓ then amount ↓. Expected (top 5):
                //
                //Gaming Console → rating 5 → amount 500
                //Smartwatch → 5 → 199.99
                //Monitor 24inch → 5 → 150
                //Eggs Pack → 5 → 2.50
                //Olive Oil → 5 → 9.50
                //Hint: Comparator chaining.
                Comparator<Report> reportComparator =
                        Comparator.comparing(Report::getDeliveryDays, Comparator.reverseOrder())
                        .thenComparing(Comparator.comparing(Report::getAmount).reversed());
                List<Report> list1 = reports.stream()
                        .sorted(reportComparator)
                        .toList();
                list1.forEach(System.out::println);
                return list1;

            default:
                return Map.of("error", "Invalid problem number (1–70)");
        }
    }
}
