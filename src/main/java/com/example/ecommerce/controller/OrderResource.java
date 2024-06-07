package com.example.ecommerce.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.entities.enums.OrderStatus;
import com.example.ecommerce.services.OrderService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> orders = service.findAll();
		return ResponseEntity.ok().body(orders);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		Order order = service.findById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public void loadOrder(@PathParam("user-json.json") String fileName) throws IOException {
	    //Resource resource =resourceLoader.getResource("classpath:data/user-json.json");
	   //log.debug("Found file " + resource.getFile().getAbsolutePath());
	    //User myUser = mapper.readValue(new File("user-json.json"),User.class);
		
		User user= new User();
		user.setId(new Long(12));
		user.setName("sunilusr1");
		user.setEmail("xyz@xyzusr");
		user.setPhone("1234567980");
		user.setPassword("12341234");
		
		
		
	    Order newOrder = new Order();
	    newOrder.setClient(user);
	    newOrder.setId(new Long(200));
	    newOrder.setMoment(Instant.parse("2024-06-20T19:53:07Z"));
	    newOrder.setOrderStatus(OrderStatus.PAID);
	    newOrder.setPayment(null);
	    
	   Order order = service.insert(newOrder);
	    //log.info(String.valueOf(people));
	}
	
}
