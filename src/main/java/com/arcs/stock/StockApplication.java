package com.arcs.stock;

import com.arcs.stock.service.StockManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockApplication implements CommandLineRunner {

	@Autowired
	private StockManagerService stockManagerService;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stockManagerService.register();
	}
}
