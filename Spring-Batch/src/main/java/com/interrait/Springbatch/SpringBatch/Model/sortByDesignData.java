package com.interrait.Springbatch.SpringBatch.Model;

public class sortByDesignData {
	private String depName;
	private String designation;
	private Long empCount;
	private Long totalSalary;
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
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
		return "sortByDesignData [depName=" + depName + ", designation=" + designation + ", empCount=" + empCount
				+ ", totalSalary=" + totalSalary + "]";
	}
	public sortByDesignData(String depName, String designation, Long empCount, Long totalSalary) {
		super();
		this.depName = depName;
		this.designation = designation;
		this.empCount = empCount;
		this.totalSalary = totalSalary;
	}
	public sortByDesignData() {
		super();
	}
	
}
