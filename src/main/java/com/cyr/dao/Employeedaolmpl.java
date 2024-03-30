package com.cyr.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.springmvc.model.Employee;

@Repository
public class Employeedaolmpl implements EmployeeDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		System.out.println("dao layer...");
      session.persist(employee);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listEmployees() {
		Session session = sessionFactory.getCurrentSession();
		List<Employee> EmployeesList = session.createQuery("from Employee").list();
		return EmployeesList;
	}

	@Override
	public void updateEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		session.update(employee);
	}
	@Override
	public Employee getEmployeeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, new Integer(id));
		return employee;
	}
	@Override
	public List<Employee> getEmployeeByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query q=session.createQuery("from Employee where firstName =:name");  
		 q.setParameter("name", name);
		  return q.getResultList();
	}

	@Override
	public void removeEmployee(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, new Integer(id));
		if (null != employee) {
			session.delete(employee);
		}
	}


}
