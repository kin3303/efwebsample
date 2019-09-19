package com.demo.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.demo"})
@EnableJpaRepositories({"com.demo"})
@EntityScan({"com.demo"})
public class SpringBootAppApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootAppApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
	
}