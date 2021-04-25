package net.openobject.ekko.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableJpaRepositories(basePackages = "net.openobject.ekko", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@Slf4j
public class DatabaseConfig {
	
    @Value("${database.username}")
    private String dbUsername;
    @Value("${database.password}")
    private String dbPassword;
    
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
    	log.debug("dbUsername: {}, dbPassword: {}", dbUsername, dbPassword);
    	DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(dbUsername);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }
    
    @Primary
    @Bean(name = "jpaProperties")
    public JpaProperties jpaProperties() {
    	return new JpaProperties();
    }
    
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    		EntityManagerFactoryBuilder builder,
    		@Qualifier("dataSource") DataSource primaryDataSource,
    		@Qualifier("jpaProperties") JpaProperties jpaProperties) {
    	return builder
    			.dataSource(primaryDataSource)
		        .properties(jpaProperties.getProperties())
		        .packages("net.openobject.ekko")
		        .persistenceUnit("default")
		        .build();
    }
    
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }
    
}
