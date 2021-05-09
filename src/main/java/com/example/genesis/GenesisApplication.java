package com.example.genesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@SpringBootApplication
@EnableJms
public class GenesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenesisApplication.class, args);
	}

}
