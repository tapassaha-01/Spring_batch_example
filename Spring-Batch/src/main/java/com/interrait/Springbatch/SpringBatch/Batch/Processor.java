package com.interrait.Springbatch.SpringBatch.Batch;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.Model.Employee;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Employee, EmployeeEntity> {

private static final Map<String, Long> SALARY_VALUE=new HashMap<String,Long>();
	
 	public Processor() {
 		SALARY_VALUE.put("A", 60000L);
 		SALARY_VALUE.put("B", 45000L);
 		SALARY_VALUE.put("C", 25000L);
 	}

    @Override
    public EmployeeEntity process(Employee emp) throws Exception {
    	String designation = emp.getDesignation();
		 Long salary = SALARY_VALUE.get(designation);
		return new EmployeeEntity(emp.getId(),emp.getName(),emp.getAddress(),salary);
    }
}
