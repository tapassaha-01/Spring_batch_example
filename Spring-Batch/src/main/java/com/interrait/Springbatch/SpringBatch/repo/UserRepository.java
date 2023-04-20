package com.interrait.Springbatch.SpringBatch.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.interrait.Springbatch.SpringBatch.Model.EmployeeDetails;
import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

public interface UserRepository extends JpaRepository<EmployeeEntity, Long> {
	
	@Query(value="select distinct(address) as dis_address, count(name) as resident, sum(salary) from emp_table group by address",nativeQuery = true)
	public List<EmployeeDetails> getCountedData();
}
