package net.openobject.ekko.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.payload.JwtUserResponse;
import net.openobject.ekko.common.auth.payload.SignupRequest;
import net.openobject.ekko.user.builder.UserBuilder;
import net.openobject.ekko.user.dto.UserInfoRequest;
import net.openobject.ekko.user.entity.User;
import net.openobject.ekko.user.entity.UserERole;
import net.openobject.ekko.user.entity.UserRole;
import net.openobject.ekko.user.repository.UserRepository;
import net.openobject.ekko.user.repository.UserRoleRepository;

/**
 * UserService.java
 * <br/>
 * 사용자 서비스
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserBuilder userBuilder;
	
	@Transactional(readOnly = true)
	public boolean existsByUserId(String userId) {
		return userRepository.existsByUserId(userId);
	}
	
	@Transactional(readOnly = true)
	public UserRole findByName(UserERole userERole){
		return userRoleRepository.findByName(userERole);
	}
	
	@Transactional(readOnly = false)
	public void registerUser(SignupRequest signUpReq) {
		User user = new User(signUpReq.getUserId(), encoder.encode(signUpReq.getPassword()), "testname", signUpReq.getUserEmailAddr(), signUpReq.getRole());
		userRepository.save(user);
	}
	
	@Transactional(readOnly = false)
	public void registerUser(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly = false)
	@CachePut(key = "#userInfoReq.userId", value = "UserDetails")
	public JwtUserResponse modifyUser(UserInfoRequest userInfoReq) {
		
		User userEintity = userRepository.findByUserId(userInfoReq.getUserId()).orElse(null);
		userInfoReq.setNewPassword(encoder.encode(userInfoReq.getNewPassword()));
		userEintity = userEintity.update(userInfoReq);
		
		return userBuilder.buildJwtUserDto(userEintity);
	}
	
	@Transactional(readOnly = true)
	@Cacheable(key = "#userId", value = "UserDetails")
	public JwtUserResponse findByUserId(String userId) {
		log.info("findByUserId {}", userId);
		User userEintity =  userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));
		
		return userBuilder.buildJwtUserDto(userEintity);
	}
}
