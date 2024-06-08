
package com.example.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.OrderItem;
import com.example.ecommerce.entities.Payment;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.entities.enums.OrderStatus;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.OrderItemRepository;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(null,"tstusr1", "joao2@gmail.com", "85999999999", "123");
		User user2 = new User(null,"tstusr2", "bruno2@gmail.com", "85999999991", "123");
		
		Order order1 = new Order(null, Instant.parse("2024-06-20T19:53:07Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-07-19T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user1);
		Order order3 = new Order(null, Instant.parse("2024-07-20T21:21:22Z"), OrderStatus.WAITING_PAYMENT,user2);
		
		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		
		Product product1 = new Product(null, "The Lord", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Tablet", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "MacBook", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product product4 = new Product(null, "PC Game", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Java for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		//---------------------
		Product product6 = new Product(null, "OneProdManyCat", "test desc", 90.5, "");

		Category category_1 = new Category();
		category_1.setName("sbc1");
		category_1.setId(new Long(1));
		
		Category category_2 = new Category();
		category_2.setName("sbc2");
		category_2.setId(new Long(2));
		
		Product product_x = new Product();
		
		
		//Set<Category> categories = new HashSet<Category>();
		//categories.add(category_1);
		//categories.add(category_2);
		
		product6.getCategories().add(category_1);
		product6.getCategories().add(category_2);
		
		//---
		
		Category category_3 = new Category();
		category_3.setName("cat_w_mult_prods");
		category_3.setId(new Long(3));
		
		Product product8 = new Product(new Long(32), "prod8", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product9 = new Product(new Long(33), "prod9", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		
		category_3.getProducts().add(product8);
		category_3.getProducts().add(product9);
		
		
		
		
		
		
		
		
		
		
		
		///------------------------
		
		userRepository.saveAll(Arrays.asList(user1,user2));
		orderRepository.saveAll(Arrays.asList(order1,order2,order3));
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3,category_1,category_2, category_3));
		productRepository.saveAll(Arrays.asList(product1, product2, product4, product3, product5,product6));
		
		product1.getCategories().add(category2);
		product2.getCategories().add(category1);
		product2.getCategories().add(category3);
		product3.getCategories().add(category3);
		product4.getCategories().add(category3);
		product5.getCategories().add(category2);
		
		productRepository.saveAll(Arrays.asList(product1, product2, product4, product3, product5));
		
		OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
		OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
		
		Payment payment1 = new Payment(null, Instant.parse("2023-06-20T21:53:07Z"), order1);
		order1.setPayment(payment1);
		orderRepository.save(order1);
	}

}
