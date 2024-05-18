package com.examples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examples.entity.User;
import com.examples.service.CRUDService;

@RestController
public class CRUDController {

	@Autowired
	CRUDService crudService;

	@GetMapping("/user/{document_id}")
	public User getUser(@PathVariable("document_id") String document_id) {
		return crudService.getUser(document_id);
	}

	@GetMapping("/users")
	public List<User> getAll() {
		return crudService.getAll();
	}

	@PostMapping("/user")
	public String addUser(@RequestBody User user) {
		return crudService.addUser(user);
	}
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		return  ResponseEntity.ok("EndPoint running ");
	}
}
