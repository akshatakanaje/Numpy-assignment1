package com.ninja.service;

import java.util.List;

import com.ninja.entity.Employee;

public interface EmployeeService {

	Employee findById(Integer id);

	Employee save(Employee employee);

	void deleteById(Integer id);

	List<Employee> getAllEmployees();

	boolean existsByEmail(String email);

	boolean existsById(Integer id);

}
