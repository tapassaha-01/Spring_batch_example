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
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interrait.Springbatch.SpringBatch.SpringBatchApplication;
import com.interrait.Springbatch.SpringBatch.Batch.MultiSheetExcelReader;
import com.interrait.Springbatch.SpringBatch.Configuration.Config;
import com.interrait.Springbatch.SpringBatch.Model.Dept_Mst;
import com.interrait.Springbatch.SpringBatch.Model.Designation_Mst;
import com.interrait.Springbatch.SpringBatch.Model.EmpAnalysisData;
import com.interrait.Springbatch.SpringBatch.Model.mst_table;
import com.interrait.Springbatch.SpringBatch.repo.Dept_mst_Repo;
import com.interrait.Springbatch.SpringBatch.repo.Designation_Mst_Repo;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;
import com.interrait.Springbatch.SpringBatch.service.BatchService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/add")
public class Controller {
	
	Logger logger = LoggerFactory.getLogger(Controller.class);
	
	private static final String[] SHEETNAME = {"Sheet1","Sheet2","Sheet3"};
	
	private static int flag=0;
	
	@Autowired
	private Config configuration;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private BatchService batchSerice;
	
	@Autowired
	private Dept_mst_Repo deptRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private Designation_Mst_Repo designRepo;
	
//	@Autowired
//	private MultiSheetExcelReader multiReader;
	
//	@PostMapping("/uploadMSt")
//	public Dept_Mst uploadMst(@RequestBody Dept_Mst dept){
//		System.out.println(dept.toString());
//		List<Designation_Mst> design = dept.getDesignation();
//		designRepo.saveAll(design);
//		
//		for(Designation_Mst i:design) {
//			System.out.println(i.toString());
//			i.setDept(dept);
////			designRepo.save(i);
//		}
//		
//		dept.setDesignation(design);
////		System.out.println(dept.toString());
////		Dept_Mst result = 
//		Dept_Mst result = deptRepo.save(dept);
//		return result;
////		return dept;
//	}
	
	
	@PostMapping("/upload")
    public BatchStatus load(@RequestParam("file") MultipartFile file,@RequestParam("sheetName") String sheetName){
//		MultiSheetExcelReader multiReader = new MultiSheetExcelReader(file,"Sheet1");
		if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			logger.info("File reached to the controller successfully!! "+file.getOriginalFilename()); 
			
		}
       Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
       JobParameters parameters = new JobParameters(maps);
       JobExecution jobExecution = new JobExecution(1L);
	try {
		jobExecution = jobLauncher.run(configuration.ReaderJob(batchSerice.reader(file,sheetName)), parameters);
		 logger.info("JobExecution: " + jobExecution.getStatus());

	      logger.info("Batch is Running...");
	       while (jobExecution.isRunning()) {
	           logger.info("...");
	       }
	       if(deptRepo.count()==0 && designRepo.count()==0) {
	    	   batchSerice.getListofMstTables();
//	    	   flag=1;
	    	   flag+=1;
	       }
	       
			
	     
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
			| JobParametersInvalidException e) {
		
		logger.error(e.getMessage());
	}

	
		return jobExecution.getStatus();
	
      
    }
	


//	
	@PostMapping("/getData")
	public List<EmpAnalysisData> gettableData(@RequestParam String option,@RequestParam String value) {
		return batchSerice.gettableData(option,value);
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
	@GetMapping("/getDeptmst")
	public List<String> getDepartMst() {
		List<String> list =  userRepo.findDistinctDeptName();
		List<String> Dept_mst_lst = new ArrayList<String>();
		for(String i:list) {
			Dept_mst_lst.add(i);
		}
		return Dept_mst_lst;
	}
	
	@GetMapping("/getDesignmst")
	public List<String> findDesignationmst() {
		List<String> list =  userRepo.findDistinctDesignation();
		List<String> Design_mst_lst = new ArrayList<String>();
		for(String i:list) {
			Design_mst_lst.add(i);
		}
		return Design_mst_lst;
	}
	
	@GetMapping("/getmsttable")
	public mst_table getMstList(){
		return batchSerice.getListofMstTables();
//		return 
	}
	
}
