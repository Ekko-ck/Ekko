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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.sample.dto.SampleUserRequest;
import net.openobject.ekko.sample.dto.SampleUserResponse;
import net.openobject.ekko.sample.service.SampleUserService;

@Api(tags = "샘플 > 사용자")
@Slf4j
@RestController
@RequestMapping("/api/sample")
public class SampleUserController {
	
	@Autowired
	private SampleUserService sampleUserService;
	
	@ApiOperation(value = "샘플 > 사용자 전제 조회", notes = "사용자 전제 조회")
	@GetMapping("/users")
	public ApiResponse<List<SampleUserResponse>> getUsers() {
		List<SampleUserResponse> userList = sampleUserService.getUserList();
		log.info("users: {}", userList);
		return ApiResponse.ok(userList);
	}
	
	@ApiOperation(value = "샘플 > 사용자 조회", notes = "사용자 조회")
	@GetMapping("/user/{id}")
	public ApiResponse<SampleUserResponse> getUser(@PathVariable @ApiParam(value = "사용자시퀀스") Long id) {
		SampleUserResponse user = sampleUserService.getUser(id);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@ApiOperation(value = "샘플 > 사용자 등록", notes = "사용자 등록")
	@PostMapping("/user")
	public ApiResponse<SampleUserResponse> postUser(@RequestBody SampleUserRequest sampleUserFormDto) {
		SampleUserResponse user = sampleUserService.registerUser(sampleUserFormDto);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@ApiOperation(value = "샘플 > 사용자 변경", notes = "사용자 변경")
	@PutMapping("/user")
	public ApiResponse<SampleUserResponse> putUser(@RequestBody SampleUserRequest sampleUserFormDto) {
		SampleUserResponse user = sampleUserService.modifyUser(sampleUserFormDto);
		log.info("user: {}", user);
		return ApiResponse.ok(user);
	}
	
	@ApiOperation(value = "샘플 > 사용자 삭제", notes = "사용자 삭제")
	@DeleteMapping("/user/{id}")
	public ApiResponse<SampleUserResponse> deleteUser(@PathVariable  @ApiParam(value = "사용자시퀀스") Long id) {
		sampleUserService.removeUser(id);
		return ApiResponse.ok();
	}
    
}
