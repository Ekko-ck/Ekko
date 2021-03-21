package net.openobject.ekko.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.sample.dto.SampleUserResponse;
import net.openobject.ekko.sample.dto.SampleUserRequest;
import net.openobject.ekko.sample.service.SampleUserService;

@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleUserController {
	
	@Autowired
	private SampleUserService sampleUserService;
	
	@GetMapping("/users")
	public ApiResponse<List<SampleUserResponse>> getUsers() {
		List<SampleUserResponse> userList = sampleUserService.getUserList();
		log.info("users: {}", userList);
		return ApiResponse.ok(userList);
	}
	
	@GetMapping("/user/{id}")
	public ApiResponse<SampleUserResponse> getUser(@PathVariable Long id) {
		SampleUserResponse user = sampleUserService.getUser(id);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@PostMapping("/user")
	public ApiResponse<SampleUserResponse> postUser(@RequestBody SampleUserRequest sampleUserFormDto) {
		SampleUserResponse user = sampleUserService.registerUser(sampleUserFormDto);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@PutMapping("/user")
	public ApiResponse<SampleUserResponse> putUser(@RequestBody SampleUserRequest sampleUserFormDto) {
		SampleUserResponse user = sampleUserService.modifyUser(sampleUserFormDto);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@DeleteMapping("/user/{id}")
	public ApiResponse<SampleUserResponse> deleteUser(@PathVariable Long id) {
		sampleUserService.removeUser(id);
		return ApiResponse.ok();
	}
    
}
