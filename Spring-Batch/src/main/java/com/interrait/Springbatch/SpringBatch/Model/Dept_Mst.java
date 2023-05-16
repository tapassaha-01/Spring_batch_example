package com.interrait.Springbatch.SpringBatch.Model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dept_mst")
public class Dept_Mst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String department;
	
	
	@OneToMany(mappedBy = "dept")
	private List<Designation_Mst> designation;
	
	public String getDepartmentName() {
		return department;
	}
	public void setDepartmentName(String departmentName) {
		this.department = departmentName;
	}
	public List<Designation_Mst> getDesignation() {
		return designation;
	}
	public void setDesignation(List<Designation_Mst> designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Dept_Mst [id=" + id + ", DepartmentName=" + department + ", designation=" + designation + "]";
	}
	public Dept_Mst() {
		super();
	}
	public Dept_Mst(String departmentName, List<Designation_Mst> designation) {
		super();
		this.department = departmentName;
		this.designation = designation;
	}
	
	
}
