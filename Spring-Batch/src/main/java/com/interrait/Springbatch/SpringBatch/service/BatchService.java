package com.interrait.Springbatch.SpringBatch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.interrait.Springbatch.SpringBatch.SpringBatchApplication;
//import com.interrait.Springbatch.SpringBatch.Batch.RowMapperImp;
import com.interrait.Springbatch.SpringBatch.Model.Employee;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeDetails;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;






@Service
public class BatchService {
	
	
	Logger logger =LoggerFactory.getLogger(BatchService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	public ItemStreamReader<Employee> reader(MultipartFile file){
		PoiItemReader<Employee> reader = new PoiItemReader<>();
		reader.setName("Xcel-Reader");
		reader.setResource( file.getResource());
		logger.info("Inside Mapper");
		reader.setRowMapper(excelRowMapperImp());
		reader.setLinesToSkip(1);
		return reader;
	}

	private RowMapper<Employee> excelRowMapperImp() {
		
		BeanWrapperRowMapper<Employee> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(Employee.class);
		return rowMapper;
	}
	
	public List<EmployeeEntity> getAllData(){
		List<EmployeeEntity> list = userRepo.findAll();
		list.stream().map(n->{logger.info(n.toString());
							return n;});
//		logger.info(list);
		return list;
	}
	public List<EmployeeDetails> getEmpDetails() throws SQLException{
		List<EmployeeDetails> resultList = new ArrayList<EmployeeDetails>();
		List<Object[]> rs = userRepo.getCountedData();
		for(Object[] rst:rs) {
			if(rst!=null) {
//				logger.info(rst[1].toString());
				resultList.add(new EmployeeDetails((String)rst[0],Integer.parseInt(rst[1].toString()),Long.parseLong(rst[2].toString())));
			}
//			logger.info(rst.getString("dis_address")+rst.getLong("resident"));
//			resultList.add(new EmployeeDetails(rst.getString("dis_address"),rst.getLong("resident")));
		}
		return resultList;
	}
}
