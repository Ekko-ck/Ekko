package net.openobject.ekko.sample.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SampleCloudConfig {
	
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.password}")
    private String dbPassword;
    
    @PostConstruct
    public void postConstruct() {
    	log.info("username: {}, password: {}", dbUsername, dbPassword);
    }
	
}
