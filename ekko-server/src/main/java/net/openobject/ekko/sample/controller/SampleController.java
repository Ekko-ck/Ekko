package net.openobject.ekko.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
	
	@GetMapping("/hello/{input}")
	public String sayHello(@PathVariable String input) {
		System.out.print("hello");
		return input;
	}
    
}
