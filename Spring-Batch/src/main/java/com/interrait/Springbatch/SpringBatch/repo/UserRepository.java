package com.interrait.Springbatch.SpringBatch.repo;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.interrait.Springbatch.SpringBatch.Model.Emp;

public interface UserRepository extends JpaRepository<Emp, Long> {
	
	@Query(value="select distinct(emp.address) as dis_address, count(emp.name) as resident,sum(salary) as total_salary from emp_table as emp group by address",nativeQuery = true)
	public List<Object[]> getCountedData();
	@Query("SELECT DISTINCT e.deptName FROM Emp e")
	public List<String> findDistinctDeptName();
	@Query("SELECT DISTINCT e.designation FROM Emp e")
	public List<String> findDistinctDesignation();
}
