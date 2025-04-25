package org.skypro.skyshop.controller;

import org.skypro.skyshop.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/greetings")
    public String greetings(@RequestParam("Timur") String name,
                            @RequestParam("Evloev") String lastName) {
        return "Hello, " + name + " " + lastName;
    }

    @GetMapping("/counter")
    public String count() {
        counterService.countdown();
        return "Количество запросов: " + counterService.getCount();
    }
}