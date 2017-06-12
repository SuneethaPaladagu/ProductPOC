package com.company.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.company.employee.Employee;

import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@ComponentScan("com.company")
@Transactional
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;


	@RequestMapping(value = "/product")
	public ModelAndView getEmployee() {

	List <Employee> emps =employeeService.getAll();

	ModelAndView mv=new ModelAndView();
	mv.setViewName("empnew");
	mv.addObject("empList", emps);
	return mv;
	   
	}

	@RequestMapping(value = "/addProduct")
	public ModelAndView addProduct(@ModelAttribute Employee employee) {
		employeeService.save(employee);

		List <Employee> emps =employeeService.getAll();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("empnew");
		mv.addObject("empList", emps);
		return mv;
	}

	@RequestMapping(value="/login")
	public ModelAndView login() {
		ModelAndView model=new ModelAndView();
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value="/main")
	public ModelAndView main() {
		ModelAndView model=new ModelAndView();
		model.setViewName("main");
		return model;
	}


}

