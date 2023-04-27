package com.interrait.Springbatch.SpringBatch.controller;

//import java.awt.print.Pageable;
//import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interrait.Springbatch.SpringBatch.SpringBatchApplication;
import com.interrait.Springbatch.SpringBatch.Configuration.Config;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeDetails;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;
import com.interrait.Springbatch.SpringBatch.service.BatchService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/add")
public class Controller {
	
	Logger logger =LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private Config configuration;
	
	@Autowired
	private JobLauncher jobLauncher;
	

	@Autowired
	private BatchService batchSerice;
	
	
	@PostMapping("/upload")
    public BatchStatus load(@RequestParam("file") MultipartFile file){

		if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			logger.info("File reached to the controller successfully!! "+file.getOriginalFilename()); 
			
		}
       Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
       JobParameters parameters = new JobParameters(maps);
       JobExecution jobExecution = new JobExecution(1L);
	try {
		jobExecution = jobLauncher.run(configuration.ReaderJob(batchSerice.reader(file)), parameters);
		 logger.info("JobExecution: " + jobExecution.getStatus());

	      logger.info("Batch is Running...");
	       while (jobExecution.isRunning()) {
	           logger.info("...");
	       }
			
	     
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
			| JobParametersInvalidException e) {
		
		logger.error(e.getMessage());
	}

	
		return jobExecution.getStatus();
	
      
    }
	
	@PostMapping("/getall")
	public List<EmployeeEntity> getAllData() {
		List<EmployeeEntity> empList = batchSerice.getAllData();
	
		return empList;
	}
	
	@PostMapping("/getData")
	public List<EmployeeDetails> getCalculatedData() throws SQLException{
		return batchSerice.getEmpDetails();
	}
	
	
	@GetMapping("/readLog")
	public List<String> readLogFile() throws IOException{
		FileReader fileReader = new FileReader("D:\\Spring_batch\\DataSource\\log_files\\spring.log");
		Scanner sc = new Scanner(fileReader);
		List<String> lines = new ArrayList<String>();
		while(sc.hasNext()) {
		    String line = sc.nextLine();
//		    System.out.println(line);
		    if (line != null) {
		        lines.add(line);
		    } else {
		        break;
		    }
		}
		sc.close();
		return lines;



	}
	
}
