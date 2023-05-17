package com.interrait.Springbatch.SpringBatch.Batch;


import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

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
    public void write(List<? extends Emp> Emp) throws JpaSystemException,HibernateException,UnexpectedRollbackException,Exception{
//    	System.out.println(Emp.get(0));
    	
        logger.info("Data Saved for Users: " + Emp);
        try {
        userRepository.saveAll(Emp);
        }
        catch(HibernateException e) {
        	logger.info("Multiple entries found with same name: "+e.toString());
        }
        catch(JpaSystemException e) {
        	logger.info("Same name occured multiple times ...:"+e.toString());
        	
        }
        catch(UnexpectedRollbackException e) {
        	logger.info("Unexpected rollback happened for dulicate entries :"+e.toString());
        }
        
    }
}
