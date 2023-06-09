package com.interrait.Springbatch.SpringBatch.controller;

import java.io.File;
import java.io.FileInputStream;
//import java.awt.print.Pageable;
//import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;
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
	@GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        String filePath = "D:\\Spring_batch\\DataSource\\log_files\\spring.log"; // Specify the path to your text file

        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file.txt");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.TEXT_PLAIN)
                .body(new InputStreamResource(inputStream));
    }
	
//	@PostMapping("/uploadCsv")
//	public BatchStatus loadCsv(@RequestParam("file") List<MultipartFile> files) throws IOException{
//		for(MultipartFile file:files) {
//			if((!file.isEmpty())&&(file.getContentType().equals("text/csv"))) {
//				try (InputStream inputStream = file.getInputStream()) {
//					InputStreamResource resource = new InputStreamResource(inputStream);
//				System.out.println(resource);
//				}
//			}
//		}
//		return null;
//	}
	
	
	@PostMapping("/upload")
    public BatchStatus load(@RequestParam("file") List<MultipartFile> files,@RequestParam("sheetName") String sheetName) throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
       JobParameters parameters = new JobParameters(maps);
       JobExecution jobExecution = new JobExecution(1L);
//		MultiSheetExcelReader multiReader = new MultiSheetExcelReader(file,"Sheet1");
		if(files.get(0).getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			logger.info("File reached to the controller successfully!! "+files.get(0).getOriginalFilename()); 
			try {
		jobExecution = jobLauncher.run(configuration.ReaderJob(batchSerice.reader(files.get(0),sheetName)), parameters);
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
		}catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			
			logger.error(e.getMessage());
		}}
		else if(files.get(0).getContentType().equals("text/csv")){
			int index = Character.getNumericValue(sheetName.charAt(sheetName.length()-1));
			
				
					System.out.println("Inside csvReader"+index);
						jobExecution = jobLauncher.run(configuration.ReaderJob(batchSerice.itemReader(files.get(index-1))), parameters);
						logger.info("JobExecution: " + jobExecution.getStatus());

					      logger.info("Batch is Running...");
					       while (jobExecution.isRunning()) {
					           logger.info("...");
					       }
					       if(deptRepo.count()==0 && designRepo.count()==0) {
					    	   batchSerice.getListofMstTables();
//					    	   flag=1;
					    	   flag+=1;
					       }
					
		}
       
		
	      
		return jobExecution.getStatus();
	
      
    }
	
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
