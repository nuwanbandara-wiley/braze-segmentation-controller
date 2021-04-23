package com.wiley.braze.segmentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.wiley.braze.segmentation.model")
@EnableJpaRepositories(basePackages = "com.wiley.braze.segmentation.repository")
@EnableConfigurationProperties(MultipartProperties.class)
@EnableScheduling
public class SegmentationApplication {
	public static final String BASE_URL = "api/v1/";

	public static void main(String[] args) {
		SpringApplication.run(SegmentationApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}
}
