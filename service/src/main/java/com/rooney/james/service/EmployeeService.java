package com.rooney.james.service;

import com.rooney.james.model.Employee;

import java.util.List;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	List<Employee> findAllEmployees();

	void deleteEmployeeBySsn(String ssn);

	Employee findBySsn(String ssn);

//	void updateEmployee(Employee employee);
}
