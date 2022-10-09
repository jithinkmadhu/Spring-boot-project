package com.orthofx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class OwnerVehicleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnerVehicleProjectApplication.class, args);
	}

}
