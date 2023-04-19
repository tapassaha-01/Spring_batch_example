package com.interrait.Springbatch.SpringBatch.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interrait.Springbatch.SpringBatch.Model.EmployeeEntity;

public interface UserRepository extends JpaRepository<EmployeeEntity, Long> {
	
}
