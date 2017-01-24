package com.rooney.james.controller;

import com.rooney.james.model.Employee;
import com.rooney.james.service.EmployeeService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // TODO: Save employee details posted in form
    @RequestMapping(value = "/employee/save", method = RequestMethod.GET)
    public String saveEmployee(Model model) {

        Employee employee = new Employee();
        employee.setName("Jack Jones");
        employee.setJoiningDate(new LocalDate(2017, 10, 11));
        employee.setSalary(new BigDecimal(40000));
        employee.setSsn("ssn00000007");

        employeeService.saveEmployee(employee);

        model.addAttribute("employee", employee);

        return "employee";
    }
}
