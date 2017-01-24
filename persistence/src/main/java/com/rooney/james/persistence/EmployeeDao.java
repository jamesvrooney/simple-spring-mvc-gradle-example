package com.rooney.james.persistence;

import com.rooney.james.model.Employee;

public interface EmployeeDao extends EntityDao<Employee, Long>{

    void deleteEmployeeBySsn(String ssn);

    Employee findBySsn(String ssn);

//	void updateEmployee(Employee employee);
}
