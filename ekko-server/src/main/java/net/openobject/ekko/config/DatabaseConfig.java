package net.openobject.ekko.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseConfig {
	
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.password}")
    private String dbPassword;
    
    @Bean
    public DataSource getDataSource() {
    	log.debug("dbUsername: {}, dbPassword: {}", dbUsername, dbPassword);
    	DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(); 
        dataSourceBuilder.username(dbUsername); 
        dataSourceBuilder.password(dbPassword); 
        return dataSourceBuilder.build(); 
    }
    
}
