package com.revplay.revplay_catalog_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {
		"com.revplay.revplay_catalog_service",
		"com.revplay.revplay_security"
})
public class RevplayCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevplayCatalogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner schemaFixer(JdbcTemplate jdbcTemplate) {
		return args -> {
			try {
				// Final safety check for created_at column and data
				jdbcTemplate.execute("UPDATE songs SET created_at = NOW() WHERE created_at IS NULL");
			} catch (Exception ignored) {}
		};
	}

}
