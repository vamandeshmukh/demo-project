package com.lti.springboot.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.lti.springboot.demo.model.Employee;
import com.lti.springboot.demo.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SampleTests {

	@MockBean
	EmployeeRepository empRepoMock;

	@InjectMocks
	EmployeeService empServ;

	@Test
	public void testGetEmployeeById() {
		Employee emp = new Employee(2, "Sonu", 90000);
		when(empRepoMock.findById(2)).thenReturn(Optional.of(emp));
		empServ.getEmployeeById(2);
		empServ.getEmployeeById(2);
		verify(empRepoMock, times(2)).findById(2);
	}

	@Test
	public void testGetAllEmployees() {
		List<Employee> empList = new ArrayList<>();
		when(empRepoMock.findAll()).thenReturn(empList);
		assertEquals(empServ.getAllEmployees().size(), 0);
	}

	@Test
	public void testAddEmployee() {
		Employee emp = new Employee(2, "Sonu", 90000);
		when(empRepoMock.save(emp)).thenReturn(emp);
		assertEquals(empServ.addEmployee(emp), emp);
	}

}