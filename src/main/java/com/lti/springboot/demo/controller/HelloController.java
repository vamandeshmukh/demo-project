package com.lti.springboot.demo.controller;

import java.util.Arrays;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
@RestController
@CrossOrigin(origins = "*")
public class HelloController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

//	http://localhost:9090/hello

	@RequestMapping("/hello")
	public String hello() {
		LOG.info("hello");
		return "Hello world!";
	}

}
