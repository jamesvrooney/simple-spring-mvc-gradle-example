package com.rooney.james.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
//            String name = employeeService.printEmployeeMessage();/

        model.addAttribute("name", "JamesR");

        return "home";
    }
}
