package com.interrait.Springbatch.SpringBatch.Batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<Emp> {
	
	Logger logger =LoggerFactory.getLogger(DBWriter.class);
	
	 @Autowired
    private UserRepository userRepository;

   
//    public DBWriter (UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public void write(List<? extends Emp> Emp) throws Exception{
        logger.info("Data Saved for Users: " + Emp);
        userRepository.saveAll(Emp);
    }
}
