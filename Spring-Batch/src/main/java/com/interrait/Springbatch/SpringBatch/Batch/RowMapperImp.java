package com.interrait.Springbatch.SpringBatch.Batch;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import org.springframework.stereotype.Component;
//import org.springframework.batch.extensions.excel.support.rowset.*;

import com.interrait.Springbatch.SpringBatch.Model.Employee;

@Component
public class RowMapperImp implements RowMapper<Employee > {

	public RowMapperImp() {}
	
	@Override
	public Employee mapRow(RowSet rs) throws Exception {
		if (rs == null || rs.getCurrentRow() == null) {
			System.out.println("Row is null");
            return null;
        }
		System.out.println("Inside Mapper"); 
		Employee emp = new Employee();
		System.out.println(rs.getCurrentRow());
		return emp;
		
	}

}
