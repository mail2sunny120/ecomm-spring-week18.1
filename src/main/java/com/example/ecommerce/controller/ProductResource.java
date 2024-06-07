package com.example.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.services.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = service.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		 Product product = service.findById(id);
		 return ResponseEntity.ok().body(product);
	}
	
	
	@PostMapping
	public void loadProduct(@PathParam("user-json.json") String fileName) throws IOException {
	    //Resource resource =resourceLoader.getResource("classpath:data/user-json.json");
	   //log.debug("Found file " + resource.getFile().getAbsolutePath());
	    //User myUser = mapper.readValue(new File("user-json.json"),User.class);
	    Product newProduct = new Product();
	    newProduct.setDescription(new String("description"));
	    newProduct.setId(new Long(126));;
	    newProduct.setImgUrl(null);;
	    newProduct.setName(new String("myProd1"));;
	    newProduct.setPrice(new Double(200.00));
	    
	    Product product = service.insert(newProduct);
	    //log.info(String.valueOf(people));
	}
	
}
