package com.interrait.Springbatch.SpringBatch.Model;

public class EmpAnalysisData {
	private String deptName;
	private Long totalDesignation;
	private String designation;
	private Long empCount;
	private Long totalSalary;
	
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
	
	
	public Long getTotalDesignation() {
		return totalDesignation;
	}
	public void setTotalDesignation(Long totalDesignation) {
		this.totalDesignation = totalDesignation;
	}
	
	@Override
	public String toString() {
		return "EmpAnalysisData [deptName=" + deptName + ", totalDesignation=" + totalDesignation + ", designation="
				+ designation + ", empCount=" + empCount + ", totalSalary=" + totalSalary + "]";
	}
	
	public EmpAnalysisData createDepData(String depName, Long totalDesignation, Long empCount, Long totalSalary) {
		return new EmpAnalysisData(depName,totalDesignation,"null",empCount,totalSalary);
	}
	
	public EmpAnalysisData createDesignData(String depName, String designation, Long empCount, Long totalSalary) {
		return new EmpAnalysisData(depName,0L,designation,empCount,totalSalary);
	}
	
	public EmpAnalysisData(String deptName, Long totalDesignation, String designation, Long empCount,
			Long totalSalary) {
		super();
		this.deptName = deptName;
		this.totalDesignation = totalDesignation;
		this.designation = designation;
		this.empCount = empCount;
		this.totalSalary = totalSalary;
	}
	public EmpAnalysisData() {
		super();
	}
	
}
