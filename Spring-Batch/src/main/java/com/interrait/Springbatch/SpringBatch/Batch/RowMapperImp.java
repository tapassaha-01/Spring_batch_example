package com.interrait.Springbatch.SpringBatch.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

public class RowMapperImp implements RowMapper<EmployeeEntity> {

	@Override
	public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmployeeEntity emp = new EmployeeEntity(rs.getLong("id"),rs.getString("name"),rs.getString("address"),rs.getLong("salary"));
		System.out.println(emp);
		return emp;
	}

}
