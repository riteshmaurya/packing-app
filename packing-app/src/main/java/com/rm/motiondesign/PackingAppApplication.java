package com.rm.motiondesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.rm.packplanner.service.PackPlannerService;

@SpringBootApplication
@ComponentScan("com")
public class PackingAppApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PackingAppApplication.class, args);
		PackPlannerService packPlannerService = context.getBean(PackPlannerService.class);
		
		packPlannerService.parseInputForPack();
		
		packPlannerService.packItems(packPlannerService);
	}



}
