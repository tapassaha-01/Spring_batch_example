	package com.interrait.Springbatch.SpringBatch.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.interrait.Springbatch.SpringBatch.Model.Emp;

public interface UserRepository extends JpaRepository<Emp, Long> {
	
	@Query(value="select distinct(emp.address) as dis_address, count(emp.name) as resident,sum(salary) as total_salary from emp_table as emp group by address",nativeQuery = true)
	public List<Object[]> getCountedData();
	
	@Query("SELECT DISTINCT e.deptName FROM Emp e")
	public List<String> findDistinctDeptName();
	
	@Query("SELECT DISTINCT e.designation FROM Emp e")
	public List<String> findDistinctDesignation();
	
	@Query("SELECT e.designation,e.deptName FROM Emp e")
	public List<String> findDesignationAndDept();
	
	@Query(value="select dept_name,designation,count(designation) totalDesignation,count(emp_id) as Emp_count,sum(salary) as total_salary from emp_table where dept_name=:depName group by dept_name,designation", nativeQuery = true)
	public List<Object[]> listDeptData(@Param("depName") String depName);
	
	@Query(value="select dept_name,designation ,count(emp_id) as Emp_count,sum(salary) as total_salary from emp_table where designation=:design group by dept_name,designation", nativeQuery = true)
	public List<Object[]> listDesignData(@Param("design") String design);
	
//	@Query(value="select  dept_name,count(dept_id) as Dept_count,designation,count(emp_id) as Emp_count,sum(salary) as total_salary from emp_table where :option = :value group by dept_name,designation,salary", nativeQuery = true)
//	public List<Object[]> getListData(@Param("option") String option,@Param("value") String value);
	
}
