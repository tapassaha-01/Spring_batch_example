package com.interrait.Springbatch.SpringBatch.Model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class mst_table {
	private List<String> Dept_list;
	private List<String> Design_list;
	public List<String> getDept_list() {
		return Dept_list;
	}
	public void setDept_list(List<String> dept_list) {
		Dept_list = dept_list;
	}
	public List<String> getDesign_list() {
		return Design_list;
	}
	public void setDesign_list(List<String> design_list) {
		Design_list = design_list;
	}
	@Override
	public String toString() {
		return "Mst_table [Dept_list=" + Dept_list + ", Design_list=" + Design_list + "]";
	}
	public mst_table() {
		super();
	
	}
	public mst_table(List<String> dept_list, List<String> design_list) {
		super();
		Dept_list = dept_list;
		Design_list = design_list;
	}
	
}
