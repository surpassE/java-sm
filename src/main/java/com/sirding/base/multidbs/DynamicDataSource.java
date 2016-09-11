package com.sirding.base.multidbs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  

public class DynamicDataSource extends AbstractRoutingDataSource {  
  
    protected Object determineCurrentLookupKey() {  
        return DataSourceContextHolder.getDataSourceType();  
    }  
  
} 