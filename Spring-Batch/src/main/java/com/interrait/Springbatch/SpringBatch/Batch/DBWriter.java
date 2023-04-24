package com.interrait.Springbatch.SpringBatch.Batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.SpringBatchApplication;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<EmployeeEntity> {
	
	Logger logger =LoggerFactory.getLogger(DBWriter.class);
	
	 @Autowired
    private UserRepository userRepository;

   
//    public DBWriter (UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public void write(List<? extends EmployeeEntity> EmployeeEntity) throws Exception{
        logger.info("Data Saved for Users: " + EmployeeEntity);
        userRepository.saveAll(EmployeeEntity);
    }
}
