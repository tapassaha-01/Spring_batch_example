package com.interrait.Springbatch.SpringBatch.Batch;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
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
    private Workbook workbook;
    private EmpDto emp;
    private Iterator<Row> rowIterator;
   
    

    public MultiSheetExcelReader(MultipartFile file, String sheetName) throws EncryptedDocumentException, IOException {
        this.file = file;
        this.sheetName = sheetName;
        this.rowIndex=0;
        FileInputStream fis = (FileInputStream) file.getInputStream();
        try (Workbook workbook = WorkbookFactory.create(fis)) {
        	Sheet sheet = workbook.getSheet(sheetName);
        	rowIterator = sheet.iterator();}
    }

    
	@Override
	public EmpDto read()  {

            
            	
			if(rowIterator!=null && rowIterator.hasNext()) 
			{
				if(rowIndex==0) {
					rowIndex++;
					rowIterator.next();
					return read();
				}
				else {
				System.out.println(rowIndex);
				Row currentRow = rowIterator.next();
				String dateString;
//				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//				 LocalDate localDate = LocalDate.parse(dateString, formatter);
//				 java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//		         if(currentRow.getCell(0).getCellType()!=CellType.BLANK ) {
		        	 if(currentRow.getCell(4).getCellType()==CellType.STRING) {
		        		 dateString = currentRow.getCell(0).getStringCellValue();
		        	 }else {
		        	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		        	 System.out.println(currentRow.getCell(4).getCellType());
		        	  dateString = dateFormat.format(currentRow.getCell(4).getDateCellValue());
		        	  }
		         emp = new EmpDto(currentRow.getCell(0).getStringCellValue(), currentRow.getCell(1).getStringCellValue(),currentRow.getCell(2).getStringCellValue(),currentRow.getCell(3).getStringCellValue(),dateString,currentRow.getCell(5).getStringCellValue(),currentRow.getCell(6).getStringCellValue());
		         }
		         return emp;
		
				
			}
			else {
	            closeWorkbook();
	            return null;
	        }
			
			
				
			
            }
	private void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (Exception e) {
            // Handle exception
        }
    }
            }
            
			
            
	
			
            

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
	


