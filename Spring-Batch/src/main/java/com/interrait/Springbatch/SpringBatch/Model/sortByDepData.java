package com.interrait.Springbatch.SpringBatch.Model;

public class sortByDepData {
	private String depName;
	private Long totalDesignation;
	private Long empCount;
	private Long totalSalary;
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Long getTotalDesignation() {
		return totalDesignation;
	}
	public void setTotalDesignation(Long totalDesignation) {
		this.totalDesignation = totalDesignation;
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
		return "sortByDepData [depName=" + depName + ", totalDesignation=" + totalDesignation + ", empCount=" + empCount
				+ ", totalSalary=" + totalSalary + "]";
	}
	public sortByDepData(String depName, Long totalDesignation, Long empCount, Long totalSalary) {
		super();
		this.depName = depName;
		this.totalDesignation = totalDesignation;
		this.empCount = empCount;
		this.totalSalary = totalSalary;
	}
	public sortByDepData() {
		super();
	}
	
}
