package com.ninja.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.dto.ResponseDto;
import com.ninja.entity.Employee;
import com.ninja.exceptions.AlreadyExistException;
import com.ninja.exceptions.NotFoundException;
import com.ninja.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * Get all employees 
	 * @return
	 */
	@GetMapping
    public List<Employee> getAllEmployees(){
         return employeeService.getAllEmployees();
    }
	
	/**
	 * Get employee by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Employee getOne(@PathVariable("id") Integer id){
		return employeeService.findById(id);
	}
	
	/**
	 * Save Emplyee data
	 * @param employee
	 * @return
	 */
	@PostMapping
	public Employee save(@RequestBody Employee employee) {
		boolean exists = employeeService.existsByEmail(employee.getEmail());
		if(!exists) {
			return employeeService.save(employee);
		}
		throw new AlreadyExistException("Employee data already exist with email '" + employee.getEmail() + "'");
	}

	/**
	 * Update employee data
	 * @param employee
	 * @return
	 */
	@PutMapping
	public Employee update(@RequestBody Employee employee) {
		boolean exists = employeeService.existsById(employee.getId());
		if(exists) {
			return employeeService.save(employee);
		}
		throw new AlreadyExistException("Employee already exist with Id '" + employee.getId() + "'");
	}
	
	/**
	 * Delete  employee by id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseDto deleteOne(@PathVariable("id") Integer id) {
		boolean exists = employeeService.existsById(id);
		if(exists) {
			employeeService.deleteById(id);
			return new ResponseDto("Success","Employee data is deleted", null);
		}
		throw new NotFoundException("Employee data does not exist with id '" + id + "'");
	}	
	
}
