package com.interrait.Springbatch.SpringBatch.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="designation_mst")
public class Designation_Mst {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String designName;
	
	@ManyToOne
	@JoinColumn(name = "dept")
	private Dept_Mst dept;
	
	public String getDesignationType() {
		return designName;
	}
	public void setDesignationType(String designation) {
		this.designName = designation;
	}
	public Dept_Mst getDept() {
		return dept;
	}
	public void setDept(Dept_Mst dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Designation_Mst [id=" + id + ", designationType=" + this.designName + ", dept=" + dept + "]";
	}
	public Designation_Mst() {
		super();
	}
	public Designation_Mst(String designName, Dept_Mst dept) {
		super();
		this.designName = designName;
		this.dept = dept;
	}
	public Designation_Mst(String designName) {
		super();
		this.designName = designName;
	}
	
	
}
