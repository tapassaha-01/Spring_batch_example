package com.interrait.Springbatch.SpringBatch.service;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.interrait.Springbatch.SpringBatch.Batch.RowMapperImp;
import com.interrait.Springbatch.SpringBatch.Model.Employee;



@Service
public class BatchService {
	
	
	public ItemStreamReader<Employee> reader(MultipartFile file){
		PoiItemReader<Employee> reader = new PoiItemReader<>();
		reader.setName("Xcel-Reader");
		reader.setResource( file.getResource());
		System.out.println("Inside Mapper");
		reader.setRowMapper(excelRowMapperImp());
		reader.setLinesToSkip(1);
	
		return reader;
	}

	private RowMapper<Employee> excelRowMapperImp() {
		
		BeanWrapperRowMapper<Employee> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(Employee.class);
		return rowMapper;
	}
}
