package com.pirest.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableResourceServer
@ComponentScan({"com.pirest.auth.service", "com.pirest.auth.config", "com.pirest.auth.controller", "com.pirest.auth.mapper"})
@EntityScan(basePackages = { "com.pirest.auth.entity" })
@EnableJpaRepositories(basePackages = { "com.pirest.auth.repository" })
@EnableTransactionManagement
public class PriestAuthenticationApplication {
	public static void main(String[] args) {
		SpringApplication.run(PriestAuthenticationApplication.class, args);
	}
}
