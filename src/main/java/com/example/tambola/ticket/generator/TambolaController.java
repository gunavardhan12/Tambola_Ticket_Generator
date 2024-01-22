//package com.example.tambola.ticket.generator;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.example.tambola.ticket.generator.TambolaTicketGenerator.generateSetOfTickets;
//
//@Controller
//@RequestMapping("/tambola")
//public class TambolaController {
//
//    @GetMapping("/get")
//    public ResponseEntity<?> generateTambolaTickets(@RequestParam int n) {
//        try {
//            List<List<List<Integer>>> tickets = generateUniqueSetOfTickets(n);
//            Map<String, List<List<List<Integer>>>> response = new HashMap<>();
//            response.put("tickets", tickets);
//            System.out.println("Generated " + tickets.size() + " tickets.");
//            return ResponseEntity.ok(response);
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//        }
//    }
//    public Map<String, List<List<List<Integer>>>> ticketDatabase = new HashMap<>();
//
//    private List<List<List<Integer>>> generateUniqueSetOfTickets(int n) {
//        List<List<List<Integer>>> setOfTickets = generateSetOfTickets(n);
//
//        for (int i = 1; i <= n; i++) {
//            String key = String.valueOf(i);
//            while (ticketDatabase.containsKey(key)) {
//                key = String.valueOf(Integer.parseInt(key) + 1);
//            }
//            ticketDatabase.put(key, setOfTickets.subList((i - 1) * 6, i * 6));
//        }
//        System.out.println("In generate unique of tickets");
//        return setOfTickets;
//    }
//}
//
package com.example.tambola.ticket.generator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tambola.ticket.generator.TambolaTicketGenerator.generateSetOfTickets;

@Controller
@RequestMapping("/tambola")
public class TambolaController {

    private static final int TICKETS_PER_SET = 6;

    @GetMapping("/get")
    public ResponseEntity<Map<String, List<List<List<Integer>>>>> generateTambolaTickets(@RequestParam int n) {
        try {
            List<List<List<Integer>>> tickets = generateUniqueSetOfTickets(n);
            Map<String, List<List<List<Integer>>>> response = new HashMap<>();
            response.put("tickets", tickets);
            System.out.println("Generated " + tickets.size() + " tickets.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception details
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private List<List<List<Integer>>> generateUniqueSetOfTickets(int n) {
        List<List<List<Integer>>> setOfTickets = generateSetOfTickets(n);

        for (int i = 1; i <= n; i++) {
            String key = String.valueOf(i);
            while (ticketDatabase.containsKey(key)) {
                key = String.valueOf(Integer.parseInt(key) + 1);
            }
            ticketDatabase.put(key, setOfTickets.subList((i - 1) * TICKETS_PER_SET, i * TICKETS_PER_SET));
        }
        System.out.println("In generate unique of tickets");
        return setOfTickets;
    }

    // Rest of the class remains unchanged

    public Map<String, List<List<List<Integer>>>> ticketDatabase = new HashMap<>();
}