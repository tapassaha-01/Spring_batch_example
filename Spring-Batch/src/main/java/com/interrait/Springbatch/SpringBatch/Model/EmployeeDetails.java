package com.interrait.Springbatch.SpringBatch.Model;

public class EmployeeDetails {

	private String dis_address;
	private int resident;
	private Long salary;
	public String getDis_address() {
		return dis_address;
	}
	public void setDis_address(String dis_address) {
		this.dis_address = dis_address;
	}
	public int getResident() {
		return resident;
	}
	public void setResident(int resident) {
		this.resident = resident;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "EmployeeDetails [dis_address=" + dis_address + ", resident=" + resident + "," + "]";
	}
	public EmployeeDetails(String dis_address, int resident,Long salary) {
		super();
		this.dis_address = dis_address;
		this.resident = resident;
		this.salary = salary;
	}
	public EmployeeDetails() {
		super();
	}
	
}
