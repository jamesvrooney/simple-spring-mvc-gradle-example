package com.rooney.james.service;

import com.rooney.james.model.Employee;
import com.rooney.james.persistence.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao dao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao dao) {
        this.dao = dao;
    }

    public void saveEmployee(Employee employee) {
        dao.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return dao.getAll();
    }

    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }

    public Employee findBySsn(String ssn) {
        return dao.findBySsn(ssn);
    }

}