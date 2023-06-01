package com.interrait.Springbatch.SpringBatch.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.extensions.excel.RowMapper;

import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.interrait.Springbatch.SpringBatch.Batch.RowMapperImp;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;
import com.interrait.Springbatch.SpringBatch.Model.mst_table;
import com.interrait.Springbatch.SpringBatch.Model.sortByDepData;
import com.interrait.Springbatch.SpringBatch.Model.sortByDesignData;
import com.interrait.Springbatch.SpringBatch.Batch.MstWriter;
import com.interrait.Springbatch.SpringBatch.Batch.MultiSheetExcelReader;
import com.interrait.Springbatch.SpringBatch.Model.Dept_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Designation_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.Model.EmpAnalysisData;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;
import com.interrait.Springbatch.SpringBatch.util.Master_table;

import ch.qos.logback.classic.pattern.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
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
	
	
	
//	public ItemStreamReader<EmpDto> reader(MultipartFile file){
//		PoiItemReader<EmpDto> reader = new PoiItemReader<>();
//		reader.setName("Xcel-Reader");
//		reader.setResource( file.getResource());
//		logger.info("Inside Mapper");
//		reader.setRowMapper(excelRowMapperImp());
//		reader.setLinesToSkip(1);
//		return reader;
//	}
	
	public ItemReader<EmpDto> reader(MultipartFile file,String sheetName){
		
		return new MultiSheetExcelReader(file,sheetName);
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

	
	
	
public List<EmpAnalysisData> gettableData(String option,String value) {
	List<Object[]> tableList = new ArrayList<>();
	List<EmpAnalysisData> dataList = new ArrayList<>();
	EmpAnalysisData empData = new EmpAnalysisData();
	
//	System.out.println(table);
	if(option.equals("Department")) {
		tableList = userRepo.listDeptData(value);
		for(Object[] i:tableList) {
			dataList.add(empData.createDepData(i[0].toString(),i[1].toString(),Long.parseLong(i[2].toString()),Long.parseLong(i[3].toString()),Long.parseLong(i[4].toString())));
		}
	}
	else if(option.equals("Designation")){
		tableList = userRepo.listDesignData(value);
		for(Object[] i:tableList) {
			dataList.add(empData.createDesignData(i[0].toString(),i[1].toString(),Long.parseLong(i[2].toString()),Long.parseLong(i[3].toString())));
		}
		
	}
	else {
		return null;
	}
//	System.out.println(value+option);
	
	
		System.out.println(dataList);
		return dataList;
	}
	
	
	public mst_table getListofMstTables() {

		List<String> dept_list = new ArrayList<>();
		List<String> design_list = new ArrayList<>();
		for(Map.Entry<String, List<String>> entry:Master_table.MST_TABLE.entrySet()) {
			dept_list.add(entry.getKey());
			List<Designation_Mst> desigList = new ArrayList<Designation_Mst>();
			for(String i:entry.getValue()) {
				design_list.add(i);
				desigList.add(new Designation_Mst(i));
			}
			mstWriter.insertDeptTable(new Dept_Mst(entry.getKey(),desigList));
		}
		return new mst_table(dept_list,design_list);	
	}
	
	
	
	public ItemReader<EmpDto> itemReader(MultipartFile file) throws IOException{
		
		FlatFileItemReader<EmpDto> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new InputStreamResource(file.getInputStream()));
		flatFileItemReader.setName("CsvReader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	
	public LineMapper<EmpDto> lineMapper() {
		DefaultLineMapper<EmpDto> defaultLineMapper = new DefaultLineMapper<>();
		String[] fileds = {"deptId","deptName","empId","empName","doj","status","designation"};
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(fileds);
		
		BeanWrapperFieldSetMapper<EmpDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(EmpDto.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}
}
