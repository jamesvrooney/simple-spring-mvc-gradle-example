package com.rooney.james.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//@Service("employeeService")
@Service
public class EmployeeServiceImpl implements EmployeeService {

	public String printEmployeeMessage() {
		return "My name is James. I'm an employee.";
	}
}
