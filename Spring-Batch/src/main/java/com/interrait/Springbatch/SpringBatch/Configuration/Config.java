package com.interrait.Springbatch.SpringBatch.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.batch.item.excel.poi.PoiItemReader;
import com.interrait.Springbatch.SpringBatch.Batch.DBWriter;
import com.interrait.Springbatch.SpringBatch.Batch.Processor;
import com.interrait.Springbatch.SpringBatch.Model.Employee;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

import org.apache.poi.ss.usermodel.WorkbookFactory;

@Configuration
@EnableBatchProcessing 
public class Config {
	
	@Autowired
	private JobBuilderFactory jobBuilder;
	
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Autowired
	private DBWriter dbWriter;
	
//	@Bean
//    public FlatFileItemReader<Employee> itemReader() {
////		D:\Spring_batch\DataSource
//        FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new FileSystemResource("D:\\Spring_batch\\DataSource\\EMP.csv"));
////        flatFileItemReader.setResource(source);
//        flatFileItemReader.setName("CSV-Reader");
//        flatFileItemReader.setLinesToSkip(1);
//        flatFileItemReader.setLineMapper(lineMapper());
//        return flatFileItemReader;
//    }
//	

	
	
	
	
//	@Bean
//    public LineMapper<Employee> lineMapper() {
//
//        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("id", "name", "address", "designation");
//
//        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Employee.class);
//
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return defaultLineMapper;
//    }
	
//	@Bean
//	public Processor processor() {
//		return new Processor();
//	}
//	
//	@Bean
//	public ItemWriter<EmployeeEntity> itemWriter(){
//		return new DBWriter();
//	}

	public Job initJob(ItemStreamReader<Employee> reader) {
		System.out.println("Inside job");
		return jobBuilder.get("XLXS-CONVERTOR")
				.incrementer(new RunIdIncrementer())
				.start(step(reader))
				.build();
	}
	

	public Step step(ItemStreamReader<Employee> reader) {
		
		Step step = stepBuilder.get("XLXS-STEP")
				.<Employee,EmployeeEntity> chunk(50)
				.reader(reader)
				.processor(new Processor())
				.writer(dbWriter)
				.build();
		
		return step; 
	}






	
}
