package com.priest.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableResourceServer
@ComponentScan({"com.priest.auth.service", "com.priest.auth.config", "com.priest.auth.controller", "com.priest.auth.mapper"})
@EntityScan(basePackages = { "com.priest.auth.entity" })
@EnableJpaRepositories(basePackages = { "com.priest.auth.repository" })
@EnableTransactionManagement
@EnableDiscoveryClient
public class PriestAuthenticationApplication {
	public static void main(String[] args) {
		SpringApplication.run(PriestAuthenticationApplication.class, args);
	}
}
