package com.rooney.james.persistence;

import com.rooney.james.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl extends HibernateEntityDaoSupport<Employee, Long> implements EmployeeDao {

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Employee.class);
    }

    public void deleteEmployeeBySsn(String ssn) {
        Query query = currentSession().createSQLQuery("delete from EMPLOYEE where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }


    public Employee findBySsn(String ssn){
        Criteria criteria = currentSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("ssn",ssn));

        return (Employee) criteria.uniqueResult();
    }

	/*public void updateEmployee(Employee employee){
		getSession().update(employee);
	}*/

}
