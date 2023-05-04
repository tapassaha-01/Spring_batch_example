package com.interrait.Springbatch.SpringBatch.Batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.interrait.Springbatch.SpringBatch.Model.Emp;

public class MultiSheetExcelReader implements ItemReader<Emp> {

	@Override
	public Emp read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		return null;
	}

}
