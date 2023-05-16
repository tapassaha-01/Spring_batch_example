package com.interrait.Springbatch.SpringBatch.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="emp_table")
public class Emp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String deptId;
	@Column
	private String deptName;
	@Column
	private String empId;
	@Column
	private String empName;
	
	@Column
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doj;
	@Column
	private String status;
	@Column
	private String designation;
	@Column
	private Long salary;
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", deptId=" + deptId + ", deptName=" + deptName + ", empId=" + empId + ", empName="
				+ empName + ", doj=" + doj + ", status=" + status + ", designation=" + designation + ", salary="
				+ salary + "]";
	}
	public Emp() {
		super();
	}
	public Emp(String deptId, String deptName, String empId, String empName, Date date, String status,
			String designation, Long salary) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.empName = empName;
		this.doj = date;
		this.status = status;
		this.designation = designation;
		this.salary = salary;
	}
	
	
}
