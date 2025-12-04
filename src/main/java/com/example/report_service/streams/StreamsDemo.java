//package com.example.report_service.streams;
//
//import com.example.report_service.ReportJpaRepo;
//import com.example.report_service.entity.Report;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
////P1 – Distinct Product Names (case-sensitive)
////Objective: Return a distinct, sorted list of all productName values.
////Result type: Result type: List.
////Expected output (conceptual): Expected: 20+ unique names like Laptop Pro, Organic Rice, Winter
////Jacket, etc.
////Hint: Hint: map -> distinct -> sorted -> toList.
//@Component
//@Slf4j
//public class StreamsDemo {
//
//    @Autowired
//    static ReportJpaRepo jpaRepo;
//
//    public static void main(String[] args) {
//
//        //P1 – Distinct Product Names (case-sensitive)
//        //Objective: Return a distinct, sorted list of all productName values.
//        //Result type: Result type: List.
//        //Expected output (conceptual): Expected: 20+ unique names like Laptop Pro, Organic Rice, Winter
//        //Jacket, etc.
//        //Hint: Hint: map -> distinct -> sorted -> toList.
//        // TODO: STREAM LOGIC FOR PROBLEM 1
//        List<Report> reports = jpaRepo.findAll();
//        List<String> uniqueProdNames = reports.stream()
//                .map(Report::getProductName)
//                .distinct()
//                .toList();
//        log.info("list of products : {}", uniqueProdNames);
//        //return uniqueProdNames;
//    }
//
//
//
//}
