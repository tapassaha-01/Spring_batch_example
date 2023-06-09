package com.interrait.Springbatch.SpringBatch.Configuration;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

//import org.springframework.batch.item.excel.poi.PoiItemReader;
import com.interrait.Springbatch.SpringBatch.Batch.DBWriter;
import com.interrait.Springbatch.SpringBatch.Batch.Processor;
import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;





@Configuration
@EnableBatchProcessing 
public class Config {

	
	@Autowired
	private JobBuilderFactory jobBuilder;
	
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Autowired
	private DBWriter dbWriter;


	public Job ReaderJob(ItemReader<EmpDto> reader) {
		System.out.println("Inside job");
		return jobBuilder.get("XLXS-CONVERTOR")
				.incrementer(new RunIdIncrementer())
				.start(readerStep(reader))
				.build();
	}
	

	private Step readerStep(ItemReader<EmpDto> reader) {
		
		Step step = stepBuilder.get("XLXS-STEP")
				.<EmpDto, Emp> chunk(100)
				.reader(reader)
				.processor(new Processor())
				.writer(dbWriter)
				.taskExecutor(taskExecutor())
//				.transactionAttribute(transactionAttribute())
				.build();
		
		return step; 
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
//	    executor.setCorePoolSize(20);
//	    executor.setMaxPoolSize(20);
//	    executor.setQueueCapacity(100);
//	    executor.setThreadNamePrefix("batch-thread-");
//	    executor.initialize();
	    return executor;
	}

	@Bean
	 public DefaultTransactionAttribute transactionAttribute() {
        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setPropagationBehavior(Propagation.REQUIRED.value()); // Set the propagation behavior
        return attribute;
    }


//	private ItemWriter<EmpDto> DbWriter() {
//		// TODO Auto-generated method stub
//		FlatFileItemWriter<EmpDto> writer = new FlatFileItemWriter<>();
//		return writer;
//	}
//
//
//	private JdbcCursorItemReader<EmpDtoEntity> dataBaseReader() {
//		JdbcCursorItemReader<EmpDtoEntity> reader = new JdbcCursorItemReader<>();
//		reader.setDataSource(dataSource);
//		reader.setSql("select * from emo_table");
//		reader.setRowMapper(new RowMapperImp());
//		return reader;
//	}


	






	
}
