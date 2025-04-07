package org.example.springbank.controllers;

import org.example.springbank.services.DemoDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/demo")
public class DemoDataController {
    private final DemoDataService demoDataService;

    public DemoDataController(DemoDataService demoDataService) {
        this.demoDataService = demoDataService;
    }

//    @GetMapping("/reset")
//    public String resetDemoData() {
//        demoDataService.generateDemoData();
//        return "Demo data has been reset!";
//    }
}
