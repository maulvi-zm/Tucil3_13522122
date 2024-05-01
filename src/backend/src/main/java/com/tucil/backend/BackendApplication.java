package com.tucil.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
	public static Solver solver;

    public static void main(String[] args) {
		solver = new Solver();
        SpringApplication.run(BackendApplication.class, args);
    }
}
