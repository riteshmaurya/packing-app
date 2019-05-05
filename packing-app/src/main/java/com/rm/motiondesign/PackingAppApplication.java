package com.rm.motiondesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PackingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackingAppApplication.class, args);
	}

}
