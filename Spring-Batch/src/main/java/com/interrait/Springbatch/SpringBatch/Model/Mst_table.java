package com.interrait.Springbatch.SpringBatch.Model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Mst_table {
	private List<Dept_Mst> Dept_list;
	private List<Designation_Mst> Design_list;
	public List<Dept_Mst> getDept_list() {
		return Dept_list;
	}
	public void setDept_list(List<Dept_Mst> dept_list) {
		Dept_list = dept_list;
	}
	public List<Designation_Mst> getDesign_list() {
		return Design_list;
	}
	public void setDesign_list(List<Designation_Mst> design_list) {
		Design_list = design_list;
	}
	@Override
	public String toString() {
		return "Mst_table [Dept_list=" + Dept_list + ", Design_list=" + Design_list + "]";
	}
	public Mst_table(List<Dept_Mst> dept_list, List<Designation_Mst> design_list) {
		super();
		Dept_list = dept_list;
		Design_list = design_list;
	}
	
}
