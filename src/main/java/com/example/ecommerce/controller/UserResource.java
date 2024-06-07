package com.example.ecommerce.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ecommerce.entities.User;
import com.example.ecommerce.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import jakarta.websocket.server.PathParam;

@RestController
//@RequestMapping(value = "/users")
@RequestMapping("/users")

public class UserResource {
	
	
	private  ObjectMapper mapper =null;

	private  ResourceLoader resourceLoader =null;

	public UserResource(ObjectMapper mapper, ResourceLoader resourceLoader) {
	    this.mapper = mapper;
	    this.resourceLoader = resourceLoader;
	}

	//@PostMapping("/users/{id}")
	//@Produces({"text/plain","application/xml","application/json"})
	@PostMapping
	public void loadUser(@PathParam("user-json.json") String fileName) throws IOException {
	    Resource resource =resourceLoader.getResource("classpath:data/user-json.json");
	   //log.debug("Found file " + resource.getFile().getAbsolutePath());
	    //User myUser = mapper.readValue(new File("user-json.json"),User.class);
	    User newUser = new User();
	    newUser.setId(new Long(12));
	    newUser.setName("sunil");
	    newUser.setEmail("xyz@xyz");
	    newUser.setPhone("1234567890");
	    newUser.setPassword("1234");
	    
	   User user = service.insert(newUser);
	    //log.info(String.valueOf(people));
	}

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(user);
	}

	
	


	
	

	@PostMapping(value = "/")
	public ResponseEntity<User> insert(@RequestBody User user){
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

//	@PostMapping(value = "/users/{id}")
	//@PostMapping(value = "/users/{id}")	
	//@ResponseStatus(code=HttpStatus.CREATED)
	//public ResponseEntity<User> addnewuser(@RequestBody User user){
		//user = service.insert(user);
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		//return ResponseEntity.created(uri).body(user);
	//}	
		
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
		user = service.update(id, user);
		return ResponseEntity.ok().body(user);
	}
}
