package com.sudarshan.coronavirusapptracker.controller;

import com.sudarshan.coronavirusapptracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStates", coronavirusDataService.getAllStates());
        return "home";
    }
}
