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

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.services.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> categories = service.findAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		Category category = service.findById(id);
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public void loadCategory(@PathParam("user-json.json") String fileName) throws IOException {
	    //Resource resource =resourceLoader.getResource("classpath:data/user-json.json");
	   //log.debug("Found file " + resource.getFile().getAbsolutePath());
	    //User myUser = mapper.readValue(new File("user-json.json"),User.class);
	    Category newCategory = new Category();
	    newCategory.setId(new Long(100));
	    newCategory.setName("Mycategory1");
  
	    Category category = service.insert(newCategory);
	    //log.info(String.valueOf(people));
	}
	
	
}

