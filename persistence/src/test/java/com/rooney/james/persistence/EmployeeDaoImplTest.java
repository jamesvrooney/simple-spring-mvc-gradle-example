package com.rooney.james.persistence;

import com.rooney.james.config.TestHibernateConfiguration;
import com.rooney.james.model.Employee;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestHibernateConfiguration.class})
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void shouldGetById() throws Exception {
        Employee employee = new Employee();
        employee.setName("james");
        employee.setSsn("abc123");
        employee.setJoiningDate(new LocalDate(2010, 10, 10));
        employee.setSalary(new BigDecimal(10000));

        sessionFactory.getCurrentSession().save(employee);
        sessionFactory.getCurrentSession().flush();

        Employee actual = employeeDao.findBySsn(employee.getSsn());

        assertThat(actual, is(employee));

    }
}

