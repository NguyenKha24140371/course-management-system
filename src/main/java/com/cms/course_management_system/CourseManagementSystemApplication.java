package com.cms.course_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.cms")
@EnableJpaRepositories(basePackages = "com.cms.repository")
@EntityScan(basePackages = "com.cms.model")
public class CourseManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementSystemApplication.class, args);
	}
}