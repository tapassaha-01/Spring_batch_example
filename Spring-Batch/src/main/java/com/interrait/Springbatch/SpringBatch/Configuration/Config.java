package com.interrait.Springbatch.SpringBatch.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.interrait.Springbatch.SpringBatch.Batch.DBWriter;
import com.interrait.Springbatch.SpringBatch.Batch.Processor;
import com.interrait.Springbatch.SpringBatch.Model.Employee;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;



@Configuration
@EnableBatchProcessing 
public class Config {
	
	@Autowired
	private JobBuilderFactory jobBuilder;
	
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Autowired
	private DBWriter dbWriter;
	
	@Bean
    public FlatFileItemReader<Employee> itemReader() {

        FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/EMP.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
	
	@Bean
    public LineMapper<Employee> lineMapper() {

        DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "name", "address", "designation");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
	
//	@Bean
//	public Processor processor() {
//		return new Processor();
//	}
//	
//	@Bean
//	public ItemWriter<EmployeeEntity> itemWriter(){
//		return new DBWriter();
//	}
	@Bean
	public Job initJob() {
		return jobBuilder.get("CSV-CONVERTOR")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
	}
	
	@Bean
	public Step step() {
		
		Step step = stepBuilder.get("CSV-STEP")
				.<Employee,EmployeeEntity> chunk(50)
				.reader(itemReader())
				.processor(new Processor())
				.writer(dbWriter)
				.build();
		
		return step; 
	}

}
