package com.example.genesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@SpringBootApplication
public class GenesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenesisApplication.class, args);
	}

}
