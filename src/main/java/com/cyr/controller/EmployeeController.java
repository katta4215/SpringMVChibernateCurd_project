package com.cyr.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.springmvc.model.Employee;
import com.test.springmvc.service.EmployeeService;

@Controller

public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listemployees(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", employeeService.listEmployees());
		return "employee";	
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String emplist(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("employeeList", employeeService.listEmployees());
		return "list";
	}
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public String viewHomePage(Model model,HttpServletRequest request){
		System.out.println("search...");
		String name=request.getParameter("empname");
		System.out.println("name>>>>>>>>::"+name);
		List<Employee> employeeList =employeeService.getEmployeeByName(name);
		 model.addAttribute("employeeList", employeeList);
		return "list";	
	}
	// Same method For both Add and Update Employee
	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public String addemployee(@ModelAttribute("employee") Employee employee) {

		if (employee.getEmployeeId() == null || employee.getEmployeeId() == 0) {
			// new employee, add it
			employeeService.addEmployee(employee);
		} else {
			// existing employee, call update
			employeeService.updateEmployee(employee);
		}

		return "redirect:/list";

	}

	@RequestMapping("/employee/remove/{id}")
	public String removeemployee(@PathVariable("id") int id) {

		employeeService.removeEmployee(id);
		return "redirect:/employees";
	}

	@RequestMapping("/employee/edit/{id}")
	public String editemployee(@PathVariable("id") int id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		model.addAttribute("employeeList", employeeService.listEmployees());
		return "employee";
	}


}
