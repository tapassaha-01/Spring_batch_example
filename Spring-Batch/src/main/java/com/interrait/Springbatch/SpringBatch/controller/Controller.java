package com.interrait.Springbatch.SpringBatch.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interrait.Springbatch.SpringBatch.Configuration.Config;
import com.interrait.Springbatch.SpringBatch.service.BatchService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/add")
public class Controller {
	
	
	
	
	@Autowired
	private Config configuration;
	
	@Autowired
	private JobLauncher jobLauncher;
	

	@Autowired
	private BatchService batchSerice;
	
	@PostMapping
    public BatchStatus load(@RequestParam("file") MultipartFile file) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

		if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			System.out.println("File reached to the controller successfully!! "+file.getOriginalFilename()); 
			
		}
       Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
       JobParameters parameters = new JobParameters(maps);
       JobExecution jobExecution = jobLauncher.run(configuration.initJob(batchSerice.reader(file)), parameters);

       System.out.println("JobExecution: " + jobExecution.getStatus());

      System.out.println("Batch is Running...");
       while (jobExecution.isRunning()) {
           System.out.println("...");
       }
		
        return jobExecution.getStatus();
    }
	
}
