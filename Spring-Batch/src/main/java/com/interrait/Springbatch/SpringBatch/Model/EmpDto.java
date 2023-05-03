package com.interrait.Springbatch.SpringBatch.Model;

import java.util.Date;

public class EmpDto {

	private Long id;
	
	private String deptId;
	
	private String deptName;
	
	private String empId;
	
	private String empName;
	
	private Date doj;
	
	private String status;
	
	private String designation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "EmpDto [id=" + id + ", deptId=" + deptId + ", deptName=" + deptName + ", empId=" + empId + ", empName="
				+ empName + ", doj=" + doj + ", status=" + status + ", designation=" + designation + "]";
	}

	public EmpDto(Long id, String deptId, String deptName, String empId, String empName, Date doj, String status,
			String designation) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.empName = empName;
		this.doj = doj;
		this.status = status;
		this.designation = designation;
	}

	public EmpDto() {
		super();
	}
	
}
