package com.interrait.Springbatch.SpringBatch.Batch;


import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<EmpDto, Emp> {

private static final Map<String, Long> SALARY_VALUE=new HashMap<String,Long>();
	
 	public Processor() {
 		SALARY_VALUE.put("Trainee ", 9000L);
 		SALARY_VALUE.put("Trainee", 9000L);
 		SALARY_VALUE.put("Programmer Analyst", 25000L);
 		SALARY_VALUE.put("Associate Engineer", 45000L);
 		SALARY_VALUE.put("Senior Software Engineer", 55000L);
 		SALARY_VALUE.put("Project Lead", 65000L);
 		SALARY_VALUE.put("Project Manager", 75000L);
 		SALARY_VALUE.put("Delivery Manager", 105000L);
 		SALARY_VALUE.put("Network engineer", 35000L);
 		SALARY_VALUE.put("Admin", 85000L);
 		SALARY_VALUE.put("Finance", 80000L);
 		SALARY_VALUE.put("Human Resource", 55000L);
 	}

    @Override
    public Emp process(EmpDto emp) throws Exception {
    	 String designation = emp.getDesignation();
		 Long salary = SALARY_VALUE.get(designation);
		 System.out.println(emp+" "+salary);
		 String dateString = emp.getDoj();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate localDate = LocalDate.parse(dateString, formatter);
		 java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

		return new Emp(emp.getDeptId(),emp.getDeptName(),emp.getEmpId(),emp.getEmpName(),sqlDate,emp.getStatus(),emp.getDesignation(),salary);
		
    	
    }
}
