package com.kursovaya.lombard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/order-completed")
    public String showOrderCompleted() {
        return "order-completed";
    }
}
