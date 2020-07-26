package com.panrui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class,
		OAuth2AutoConfiguration.class,
		SecurityFilterAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class,
		DataSourceAutoConfiguration.class})
public class WebtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtestApplication.class, args);
	}

}
