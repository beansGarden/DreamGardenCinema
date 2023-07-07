package edu.kh.dgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DreamGardenCinemaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamGardenCinemaBootApplication.class, args);
	}

}
