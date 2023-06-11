package com.interrait.Springbatch.SpringBatch.Batch;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class CustomJdbcPagingItemReader<T> extends JdbcPagingItemReader<T> {

    public CustomJdbcPagingItemReader(DataSource dataSource, int pageSize, RowMapper<T> rowMapper) {
        this.setDataSource(dataSource);
        this.setPageSize(pageSize);
        this.setRowMapper(rowMapper);
        // Customize any other properties as needed
    }

    // Override any necessary methods or add custom logic here

}

