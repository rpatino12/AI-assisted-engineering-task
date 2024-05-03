package com.rpatino12.inventoryai;

import com.rpatino12.inventoryai.model.Product;
import com.rpatino12.inventoryai.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryAiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository products){
		return args -> {
			products.save(new Product("Apple", "12-pack of red apples", 12.99, 2));
			products.save(new Product("Orange juice", "52oz bottle", 15.00, 1));
			products.save(new Product("Frozen pizza", "Medium Pepperoni pizza", 16.50, 1));
			products.save(new Product("Yogurt", "6-pack assorted fruit", 8.99, 3));
			products.save(new Product("Milk", "64oz 2% milk", 3.50, 4));
			products.save(new Product("Bread", "Whole grain bread loaf", 4.00, 2));
			products.save(new Product("Eggs", "12-pack of large eggs", 2.50, 5));
			products.save(new Product("Cheese", "8oz cheddar cheese", 4.50, 2));
			products.save(new Product("Ground Beef", "1lb pack", 7.00, 3));
			products.save(new Product("Chicken Breast", "1lb pack", 6.00, 4));
		};
	}

}
