package com.websystique.springboot.config;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration

public class DataConfig {
   
    
  @Bean
    public LocalSessionFactoryBean sessionFactory(){
	Resource config = new ClassPathResource("hibernate.cfg.xml");
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setConfigLocation(config);
	sessionFactory.setPackagesToScan(("com.websystique.springboot.model"));
	sessionFactory.setDataSource(dataSource());
	return sessionFactory;		
    }
    
  @Bean
    public DataSource dataSource() {
	BasicDataSource ds = new BasicDataSource();
	
	ds.setDriverClassName(("org.h2.Driver"));
	
	ds.setUrl(("jdbc:h2:tcp://localhost/~/ROOT/Work-Spaces/SpringRest-demo/data"));
	
	ds.setUsername(("sa"));
	
	ds.setPassword((""));
	
	return ds;
    }

}