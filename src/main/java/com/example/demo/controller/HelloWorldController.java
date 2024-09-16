package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HelloWeb;
import com.example.demo.service.GreetingService;

@RestController
public class HelloWorldController {
	@Autowired
	HelloWeb helloWeb;
	
	
	@Autowired
	GreetingService greetingService;
	
	//GEt HTTP method or rest service with URI /hello
	@GetMapping("hello")
	public String sayHello() {
		return greetingService.greet();
	}
	
	@GetMapping("helloweb")
	public HelloWeb sayHelloWebWithObject() {
		//HelloWeb helloWeb = new HelloWeb();
		helloWeb.setId(1);
		helloWeb.setMessage("Hello World!!!");
		return helloWeb;
	}
	
	@GetMapping("/helloweb/name/{myname}/{age}")
	public HelloWeb sayHelloWithObjectPath(@PathVariable String myname, @PathVariable Integer age) {
		helloWeb.setId(2);
		helloWeb.setMessage("Hello " + myname + ". How are you doing?");
		return helloWeb;
	}
	
	@GetMapping("/helloweb/name")
	public HelloWeb sayHelloWithQueryParam(@RequestParam String message) {
		helloWeb.setId(2);
		helloWeb.setMessage("Hello : "+ message);
		return helloWeb;
	}
	
}


