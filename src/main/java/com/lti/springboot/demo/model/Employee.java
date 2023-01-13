package com.lti.springboot.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "emp_table", schema = "lti")
@Table(name = "emp_table")
public class Employee {

	@Id
	@Column(name = "employee_id")

	// PostGRE
//	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// MySQL
//	@GenericGenerator(name = "emp_seq", strategy = "increment")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")

//	How to generate custom values for PK column?
//  custom sequence in PostGRE
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sq")
//  @SequenceGenerator(name="your_custom_sequence", sequenceName = "emp_sq", allocationSize=1) 
//  SQL: CREATE SEQUENCE emp_sq START WITH 1010 INCREMENT BY 10;

	private int employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "salary")
	private double salary;

	public Employee() {
		super();
	}

	public Employee(String firstName, double salary) {
		super();
		this.firstName = firstName;
		this.salary = salary;
	}

	public Employee(int employeeId, String firstName, double salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", salary=" + salary + "]";
	}

}
