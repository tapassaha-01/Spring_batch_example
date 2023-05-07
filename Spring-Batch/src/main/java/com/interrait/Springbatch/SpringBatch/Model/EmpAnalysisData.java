package com.interrait.Springbatch.SpringBatch.Model;

public class EmpAnalysisData {
	private Long deptCount;
	private String deptName;
	private String designation;
	private Long empCount;
	private Long totalSalary;
	public Long getDeptCount() {
		return deptCount;
	}
	public void setDeptCount(Long deptCount) {
		this.deptCount = deptCount;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getEmpCount() {
		return empCount;
	}
	public void setEmpCount(Long empCount) {
		this.empCount = empCount;
	}
	public Long getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(Long totalSalary) {
		this.totalSalary = totalSalary;
	}
	@Override
	public String toString() {
		return "EmpAnalysisData [deptCount=" + deptCount + ", deptName=" + deptName + ", designation=" + designation
				+ ", empCount=" + empCount + ", totalSalary=" + totalSalary + "]";
	}
	public EmpAnalysisData(Long deptCount, String deptName, String designation, Long empCount, Long totalSalary) {
		super();
		this.deptCount = deptCount;
		this.deptName = deptName;
		this.designation = designation;
		this.empCount = empCount;
		this.totalSalary = totalSalary;
	}
	public EmpAnalysisData() {
		super();
	}
	
}
