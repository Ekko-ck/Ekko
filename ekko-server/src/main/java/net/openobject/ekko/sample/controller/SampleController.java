package net.openobject.ekko.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.payload.MessageResponse;
import net.openobject.ekko.common.response.ApiResponse;

@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleController {
	
	@GetMapping("/hello/{input}")
	public String sayHello(@PathVariable String input) {
		log.info("hello");
		return input;
	}
	
	@GetMapping("/hello2/{input}")
	public ApiResponse<MessageResponse> sayHello2(@PathVariable String input) {
		log.info("hello2");
		return  ApiResponse.ok(new MessageResponse(input));
	}
	
	@GetMapping("/modelandview/test")
	public ModelAndView modelandviewTest() {
		ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

	    mv.addObject("recordsTotal", "1");
	    mv.addObject("recordsFiltered", "2");
	    mv.addObject("data", "123");
		return mv;
	}
	
}
