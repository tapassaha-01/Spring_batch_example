package com.interrait.Springbatch.SpringBatch.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import com.interrait.Springbatch.SpringBatch.Model.Emp;

public class RowMapperImp implements RowMapper<Emp> {
	
	private final int sheetNumber;

    public RowMapperImp(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }
    
//	@Override
//	public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
//		
//		Emp emp = new Emp();
//		System.out.println(emp);
//		return emp;
//	}

	@Override
	public Emp mapRow(RowSet rs) throws Exception {
		String[] name = rs.getCurrentRow();
//        int age = rs.getColumnValue("age");	
		return null;
	}

}
