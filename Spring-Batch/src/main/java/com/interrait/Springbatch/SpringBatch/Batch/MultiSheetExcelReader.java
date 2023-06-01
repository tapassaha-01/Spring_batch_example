package com.interrait.Springbatch.SpringBatch.Batch;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.multipart.MultipartFile;

import com.interrait.Springbatch.SpringBatch.Model.Emp;
import com.interrait.Springbatch.SpringBatch.Model.EmpDto;


public class MultiSheetExcelReader implements ItemReader<EmpDto> {
	
	private final MultipartFile file;
    private final String sheetName;
    private int rowIndex;
    private EmpDto emp;
   
    

    public MultiSheetExcelReader(MultipartFile file, String sheetName) {
        this.file = file;
        this.sheetName = sheetName;
        this.rowIndex=1;
    }

    
	@Override
	public EmpDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

            FileInputStream fis = (FileInputStream) file.getInputStream();
            try (Workbook workbook = WorkbookFactory.create(fis)) {
            	Sheet sheet = workbook.getSheet(sheetName);
//            	System.out.println(sheet.getLastRowNum());
			if(rowIndex<=sheet.getLastRowNum()) 
			{
				Row currentRow = sheet.getRow(rowIndex);
				String dateString = currentRow.getCell(4).getStringCellValue();
//				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//				 LocalDate localDate = LocalDate.parse(dateString, formatter);
//				 java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		         if(currentRow.getCell(0)!=null) {
		         emp = new EmpDto(currentRow.getCell(0).getStringCellValue(), currentRow.getCell(1).getStringCellValue(),currentRow.getCell(2).getStringCellValue(),currentRow.getCell(3).getStringCellValue(),dateString,currentRow.getCell(5).getStringCellValue(),currentRow.getCell(6).getStringCellValue());
		         rowIndex++;}
		         else {
		        	 emp=null;
		         }}
		         
			
			
			
				workbook.close();
				return emp;
			
            }
            }}
            
			
            
	
			
            

//
//	private Emp readNextSheetData(Sheet currentSheet) {
////		List<Emp> sheetData = new ArrayList<>();
//		currentSheetIterator = currentSheet.iterator();
//        while (currentSheetIterator.hasNext()) {
//            Row row = currentSheetIterator.next();
//            // Read data from the row and create an object
//            // Customize this part according to your needs
//
//            // For example, assuming each row represents a Person object with two columns: name and age

//            java.util.Date utilDate = row.getCell(4).getDateCellValue();
//            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//            Emp object =  new Emp(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue(),sqlDate,row.getCell(5).getStringCellValue(),row.getCell(6).getStringCellValue(),(long)row.getCell(7).getNumericCellValue()); // Create the object based on your model
//
//            sheetData.add(object);
//        }
//		return sheetData;
	


