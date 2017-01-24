package com.rooney.james.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.rooney.james.service.EmployeeService;

@Controller
public class HomeController {

	private EmployeeService employeeService;

	@Autowired
        public HomeController(EmployeeService employeeService) {
                this.employeeService = employeeService;
	}
	
        @RequestMapping(value = "/")
        public String index(Model model) {
		String name = employeeService.printEmployeeMessage();

		model.addAttribute("name", name);

                return "home";
        }
}
