package com.interrait.Springbatch.SpringBatch.util;



import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Mst_table {

	public static final String[] DESIGNATION_MST_TABLE = {"Trainee" , "Programmer Analyst", "Associate Engineer", "Senior Software Engineer", "Project Lead", "Project Manager", "Delivery Manager", "Network engineer", "Admin", "Finance","Human Resource"};
	public static final String[] DEPARTMENT_MST_TABLE = {"Human Resource Department", "Finance Department", "Sales Department", "Research and Development Department", "Security and Transport Department", "IT Service Department", "Admin Department"};
	public static final Map<String, List<String>> MST_TABLE = Collections.unmodifiableMap(new HashMap<String, List<String>>() {{
       put("Human Resource Department",Arrays.asList("Human Resource"));
       put("Finance Department",Arrays.asList("Finance"));
       put("Sales Department",Arrays.asList("Delivery Manager"));
       put("Research and Development Department",Arrays.asList("Trainee"));
       put("Security and Transport Department",Arrays.asList("Network engineer"));
       put("IT Service Department",Arrays.asList("Programmer Analyst","Associate Engineer","Senior Software Engineer","Project Lead","Project Manager"));
       put("Admin Department",Arrays.asList("Admin"));
	}});
	
}

