package com.ninja.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ninja.entity.Employee;
import com.ninja.exceptions.NotFoundException;
import com.ninja.repository.EmployeeRepository;
import com.ninja.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(Integer id) {
		final Optional<Employee> optional = employeeRepository.findById(id); 
		if(optional.isPresent()) {
			return optional.get();
		}
		throw new NotFoundException("Employee data does not exist with id '"+ id +"'");
	}
	

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteById(Integer id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public boolean existsByEmail(String email) {
		return employeeRepository.existsByEmail(email);
	}

	@Override
	public boolean existsById(Integer id) {
		return employeeRepository.existsById(id);
	}


}
