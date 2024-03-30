package com.cyr.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyr.model.Employee;
import com.test.springmvc.dao.EmployeeDao;
@Transactional
@Service

public class Employeeservicelmpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);	
	}

	

	@Override
	@Transactional
	public List<Employee> listEmployees() {
		return this.employeeDao.listEmployees();
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
	}
	@Override
	@Transactional
	public List<Employee> getEmployeeByName(String name) {
		return employeeDao.getEmployeeByName(name);
	}


	@Override
	@Transactional
	public void removeEmployee(int id) {
		employeeDao.removeEmployee(id);
	}


}
