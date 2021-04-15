package net.openobject.ekko.sample.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.sample.dto.SampleUserRequest;
import net.openobject.ekko.sample.dto.SampleUserResponse;

@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleController {
	
	@GetMapping("/hello/{input}")
	public String sayHello(@PathVariable String input) {
		log.info("hello");
		return input;
	}
	
	@GetMapping("/modelandview/test")
	public ModelAndView modelandviewTest() {
		ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

	    mv.addObject("recordsTotal", "1");
	    mv.addObject("recordsFiltered", "2");
	    mv.addObject("data", "123");
		return mv;
	}
	
	@GetMapping("/modelandview/test2")
	public ModelAndView modelandviewTest2() {
		ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
		
		SampleUserResponse userRes = new SampleUserResponse();
		userRes.setUserId("sehoone");
		userRes.setUserName("세훈이");
		
		SampleUserRequest userreq = new SampleUserRequest();
		userreq.setUserId("asd");
		userreq.setUserName("ddddd");
		userRes.setSampleUserRequest(userreq);
		
		//mv.addObject(userRes);
		//mv.addObject("user", userRes);
		//ObjectMapper objectMapper = new ObjectMapper();
		//Map<String, Object> map = objectMapper.convertValue(userRes, Map.class);
		Map<String, Object> resultMap = new ObjectMapper().convertValue(userRes, new TypeReference<Map<String,Object>>(){});
		mv.addAllObjects(resultMap);
		return mv;
	}
    
}
