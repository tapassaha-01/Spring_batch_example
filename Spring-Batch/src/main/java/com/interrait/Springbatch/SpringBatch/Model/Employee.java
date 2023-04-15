package com.interrait.Springbatch.SpringBatch.Model;
	
public class Employee{
	private long id;
	private String name;
	private String address;
	private String designation;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", designation=" + designation + "]";
	}
	public Employee(long id, String name, String address, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.designation = designation;
	}
	public Employee() {
		super();
	}
	
}