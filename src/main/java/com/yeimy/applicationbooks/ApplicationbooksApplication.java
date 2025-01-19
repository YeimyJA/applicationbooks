package com.yeimy.applicationbooks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yeimy.applicationbooks.main.Main;

@SpringBootApplication
public class ApplicationbooksApplication implements CommandLineRunner {

	//@Autowired
	//private BookRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ApplicationbooksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
