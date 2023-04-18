package com.interrait.Springbatch.SpringBatch.Batch;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;
import com.interrait.Springbatch.SpringBatch.repo.UserRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<EmployeeEntity> {
	
	 @Autowired
    private UserRepository userRepository;

   
//    public DBWriter (UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public void write(List<? extends EmployeeEntity> EmployeeEntity) throws Exception{
        System.out.println("Data Saved for Users: " + EmployeeEntity);
//        userRepository.saveAll(EmployeeEntity);
    }
}
