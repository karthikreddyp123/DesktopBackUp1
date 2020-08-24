package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTestApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringTestApplication.class, args);
		Test tes=context.getBean(Test.class);
		tes.print();
	}

}
