package com.lti.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lti.springboot.demo.model.Employee;
import com.lti.springboot.demo.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class EmployeeService implements IEmployeeService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeRepository empRepository;

	@Override
	@Cacheable(value = "empCache")
	public List<Employee> getAllEmployees() {
		List<Employee> empList = empRepository.findAll();
		try {
			LOG.info("Waiting...");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOG.info(Integer.toString(empList.size()));
		return empList;
	}

	public List<Employee> getAllEmployees(int page, int size, String sort) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sort));
		Page<Employee> pagedResult = empRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Employee>();
		}
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		Optional<Employee> empOptional = empRepository.findById(employeeId);
		if (empOptional.isPresent()) {
			LOG.info(Integer.toString(employeeId));
			return empOptional.get();
		} else {
			String errorMesage = "Employee with eid " + employeeId + " is not found.";
			LOG.error(errorMesage);
//			throw new EmployeeNotFoundException(errorMesage);
			throw new RuntimeException(errorMesage);
		}
	}

	@Override
	public List<Employee> getEmployeesBySalaryGreaterThan(double salary) {
		LOG.info(Double.toString(salary));
		return empRepository.findBySalaryGreaterThanOrderBySalary(salary);
	}

	@Override
	@CachePut(value = "empCache")
	public Employee addEmployee(Employee employee) {
		LOG.info(employee.toString());
		return empRepository.save(employee);
	}

	@Override
	@CacheEvict("empCache")
	public Employee updateEmployee(Employee employee) {
		LOG.info(employee.toString());
		this.getEmployeeById(employee.getEmployeeId());
		return empRepository.save(employee);
	}

	@Override
	@CacheEvict("empCache")
	public Employee deleteEmployee(int employeeId) {
		LOG.info(Integer.toString(employeeId));
		Employee emp = this.getEmployeeById(employeeId);
		empRepository.deleteById(employeeId);
		return emp;
	}


}
//package com.lti.springboot.demo.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.lti.springboot.demo.model.Employee;
//
//@Service
////public class EmployeeServiceImpl implements EmployeeService {
//public class EmployeeService implements IEmployeeService {
//
//	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//	
//	@Override
//	public List<Employee> getAllEmployees() {
//		List<Employee> empList = new ArrayList<>();
//		empList.add(new Employee(101, "Sonu", 90000));
//		empList.add(new Employee(102, "Monu", 99000));
//		empList.add(new Employee(103, "Tonu", 95000));
//		LOG.info(empList.toString());
//		return empList;
//	}
//
//}
