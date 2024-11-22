package com.spring.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Employee;
import com.spring.demo.repository.RepositoryLayer;
@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private RepositoryLayer layer;

@GetMapping("/employees")
public List<Employee>getAllEmployess(){
	return layer.findAll();
	
}

@PostMapping("/employees")
public Employee createEmployee( @RequestBody Employee employee) {
	return layer.save(employee);
	
}



@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
	Employee employee = layer.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Employee data not matches with id:" + id));
	return ResponseEntity.ok(employee);
}



@PutMapping("/employees/{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
	Employee employee = layer.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
	
	employee.setFirstName(employeeDetails.getFirstName());
	employee.setLastName(employeeDetails.getLastName());
	employee.setEmailId(employeeDetails.getEmailId());
	
	Employee updateEmployee = layer.save(employee);
	return ResponseEntity.ok(updateEmployee);
}


@DeleteMapping("/employees/{id}")
public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
	Employee employee = layer.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
	
	layer.delete(employee);
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}


}



