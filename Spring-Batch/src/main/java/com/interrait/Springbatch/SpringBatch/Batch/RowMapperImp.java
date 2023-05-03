package com.interrait.Springbatch.SpringBatch.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.interrait.Springbatch.SpringBatch.Model.Emp;

public class RowMapperImp implements RowMapper<Emp> {

	@Override
	public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Emp emp = new Emp();
		System.out.println(emp);
		return emp;
	}

}
