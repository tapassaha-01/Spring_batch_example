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

//import com.interrait.Springbatch.SpringBatch.Batch.RowMapperImp;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;
import com.interrait.Springbatch.SpringBatch.Batch.MstWriter;
import com.interrait.Springbatch.SpringBatch.Model.Dept_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Designation_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;
import com.interrait.Springbatch.SpringBatch.util.Mst_table;

import ch.qos.logback.classic.pattern.Util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;






@Service
public class BatchService {
	
	
	Logger logger =LoggerFactory.getLogger(BatchService.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MstWriter mstWriter;
	
	
	public ItemStreamReader<EmpDto> reader(MultipartFile file){
		PoiItemReader<EmpDto> reader = new PoiItemReader<>();
		reader.setName("Xcel-Reader");
		reader.setResource( file.getResource());
		logger.info("Inside Mapper");
		reader.setRowMapper(excelRowMapperImp());
		reader.setLinesToSkip(1);
		return reader;
	}
	

	private RowMapper<EmpDto> excelRowMapperImp() {
		
		BeanWrapperRowMapper<EmpDto> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(EmpDto.class);
		return rowMapper;
	}
	
	public List<Emp> getAllData(){
		List<Emp> list = userRepo.findAll();
		list.stream().map(n->{logger.info(n.toString());
							return n;});
//		logger.info(list);
		return list;
	}
//	public List<EmpDtoDetails> getEmpDetails() throws SQLException{
//		List<EmpDtoDetails> resultList = new ArrayList<EmpDtoDetails>();
//		List<Object[]> rs = userRepo.getCountedData();
//		for(Object[] rst:rs) {
//			if(rst!=null) {
////				logger.info(rst[1].toString());
//				resultList.add(new EmpDtoDetails((String)rst[0],Integer.parseInt(rst[1].toString()),Long.parseLong(rst[2].toString())));
//			}
////			logger.info(rst.getString("dis_address")+rst.getLong("resident"));
////			resultList.add(new EmpDtoDetails(rst.getString("dis_address"),rst.getLong("resident")));
//		}
//		return resultList;
//	}
	
	public void getListofMstTables() {
//		List<String> Mst_table_list = userRepo.findDesignationAndDept();
//		List<Dept_Mst> dept_list = new ArrayList<Dept_Mst>();
		
		for(Map.Entry<String, List<String>> entry:Mst_table.MST_TABLE.entrySet()) {
			List<Designation_Mst> desigList = new ArrayList<Designation_Mst>();
			for(String i:entry.getValue()) {
				desigList.add(new Designation_Mst(i));
			}
			mstWriter.insertDeptTable(new Dept_Mst(entry.getKey(),desigList));
		}
//		System.out.println(Mst_table_list);
//		return Mst_table_list;
	}
}
