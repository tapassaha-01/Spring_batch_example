package com.interrait.Springbatch.SpringBatch.Batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.Model.Dept_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Designation_Mst;
import com.interrait.Springbatch.SpringBatch.repo.Dept_mst_Repo;
import com.interrait.Springbatch.SpringBatch.repo.Designation_Mst_Repo;

@Component
public class MstWriter {

		@Autowired
		private Dept_mst_Repo deptRepo;
		
		@Autowired
		private Designation_Mst_Repo designRepo;
	
		public void insertDeptTable(Dept_Mst dpt) {
			List<Designation_Mst> design = dpt.getDesignation();
			designRepo.saveAll(design);
			
			for(Designation_Mst i:design) {
				System.out.println(i.toString());
				i.setDept(dpt);
//				designRepo.save(i);
			}
			
			dpt.setDesignation(design);
//			System.out.println(dept.toString());
//			Dept_Mst result = 
			Dept_Mst result = deptRepo.save(dpt);
			
		}
		
		public void insertDesignTable() {
			
		}
	
}
