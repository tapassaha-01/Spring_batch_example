package com.interrait.Springbatch.SpringBatch.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.RowMapper;

//import org.springframework.batch.item.excel.poi.PoiItemReader;
import com.interrait.Springbatch.SpringBatch.Batch.DBWriter;
import com.interrait.Springbatch.SpringBatch.Batch.Processor;
import com.interrait.Springbatch.SpringBatch.Batch.RowMapperImp;
import com.interrait.Springbatch.SpringBatch.Model.Employee;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.WorkbookFactory;

@Configuration
@EnableBatchProcessing 
public class Config {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JobBuilderFactory jobBuilder;
	
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Autowired
	private DBWriter dbWriter;


	public Job ReaderJob(ItemStreamReader<Employee> reader) {
		System.out.println("Inside job");
		return jobBuilder.get("XLXS-CONVERTOR")
				.incrementer(new RunIdIncrementer())
				.start(readerStep(reader))
				.build();
	}
	

	private Step readerStep(ItemStreamReader<Employee> reader) {
		
		Step step = stepBuilder.get("XLXS-STEP")
				.<Employee,EmployeeEntity> chunk(50)
				.reader(reader)
				.processor(new Processor())
				.writer(dbWriter)
				.build();
		
		return step; 
	}
	
	
//	public Job writerJob() {
//		return jobBuilder.get("GET-MQSQL-DATA")
//				.incrementer(new RunIdIncrementer())
//				.start(writerStep())
//				.build();
//	}

//
//	private Step writerStep() {
//		
//		return stepBuilder.get("WRITER-STEP")
//				.<EmployeeEntity,Employee>chunk(50)
//				.reader(dataBaseReader())
//				.writer(DbWriter())
//				.build();
//				
//	}


	private ItemWriter<Employee> DbWriter() {
		// TODO Auto-generated method stub
		FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
		return writer;
	}


	private JdbcCursorItemReader<EmployeeEntity> dataBaseReader() {
		JdbcCursorItemReader<EmployeeEntity> reader = new JdbcCursorItemReader<>();
		reader.setDataSource(dataSource);
		reader.setSql("select * from emo_table");
		reader.setRowMapper(new RowMapperImp());
		return reader;
	}


	






	
}
