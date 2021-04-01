package net.openobject.ekko.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.payload.JwtResponse;
import net.openobject.ekko.common.auth.payload.LoginRequest;
import net.openobject.ekko.common.auth.payload.MessageResponse;
import net.openobject.ekko.common.auth.payload.SignupRequest;
import net.openobject.ekko.common.controller.BaseController;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.common.security.jwt.JwtUtils;
import net.openobject.ekko.common.security.service.UserDetailsImpl;
import net.openobject.ekko.user.dto.UserInfoRequest;
import net.openobject.ekko.user.entity.User;
import net.openobject.ekko.user.entity.UserERole;
import net.openobject.ekko.user.entity.UserRole;
import net.openobject.ekko.user.repository.UserRepository;
import net.openobject.ekko.user.repository.UserRoleRepository;

/**
 * UserController.java
 * <br/>
 * 사용자 관련 컨트롤러(로그인)
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController{
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Value("${ekkoserver.app.jwtExpirationMs}") //AccessToken 유효시간 30분
	private int jwtExpirationMs;
	/**
	 * authenticateUser(/api/auth/signin)
	 * <br/>
	 * 로그인
	 * 
	 * @author  : SeHoon
	 */
	@PostMapping("/auth/signin")
	public ApiResponse<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest,HttpServletRequest request) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtUtils.generateJwtToken(authentication);
		String refreshToken = jwtUtils.generateRefreshJwtToken(token);
		//토큰을 발행하고 같은 생명주기의 세션을 생성
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginRequest.getUserId());
		session.setMaxInactiveInterval(jwtExpirationMs/1000);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ApiResponse.ok(new JwtResponse(
				userDetails.getUserSeq(), 
				userDetails.getUsername(), 
				userDetails.getUserRealName(),
				userDetails.getUserEmailAddr(), 
				 roles,
				 token, refreshToken));
	}

	/**
	 * registerUser(/api/auth/signup) <br/>
	 * 사용자등록
	 * 
	 * @author : SeHoon
	 * @throws BizException
	 */
	@PostMapping("/auth/signup")
	public ApiResponse<MessageResponse> registerUser(@RequestBody SignupRequest signUpReq) throws BizException {
		if (userRepository.existsByUserId(signUpReq.getUserId())) {
			throw new BizException("signup001", "UserId : '"+signUpReq.getUserId()+"' 는 이미 존재합니다.");
		}

		// Create new user's account
		User user = new User(signUpReq.getUserId(), encoder.encode(signUpReq.getPassword()), "testname", signUpReq.getEmail());

		Set<String> strRoles = signUpReq.getRole();
		Set<UserRole> boRoles = new HashSet<>();

		if (strRoles == null) {
			UserRole userRole = userRoleRepository.findByName(UserERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			boRoles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					UserRole adminRole = userRoleRepository.findByName(UserERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					boRoles.add(adminRole);

					break;
				case "mod":
					UserRole modRole = userRoleRepository.findByName(UserERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					boRoles.add(modRole);

					break;
				default:
					UserRole userRole = userRoleRepository.findByName(UserERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					boRoles.add(userRole);
				}
			});
		}

		user.setRoles(boRoles);
		userRepository.save(user);

		return ApiResponse.ok(new MessageResponse("User registered successfully!"));
	}
	
	/**
	 * refreshToken(/api/auth/refreshtoken)
	 * 
	 * 사용자 토큰 재발급 
	 * 
	 * @author SeHoon
	 * @throws IOException 
	 */
	@PostMapping(value = "/auth/refreshtoken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
			String refreshToken = headerAuth.substring(7, headerAuth.length());
			try {
				if(jwtUtils.validateJwtToken(refreshToken)) {
					String newToken = jwtUtils.generateJwtToken(refreshToken);
					String newRefreshToken = jwtUtils.generateRefreshJwtToken(newToken);
					HashMap<String, String> tokens = new HashMap<String, String>();
					tokens.put("token", newToken);
					tokens.put("refreshToken", newRefreshToken);
					return getResponseEntity(tokens);
				} else {
					throw new BizException("refreshtokenE001", "토큰이 유효하지 않습니다");
				}
			} catch (Exception e) {
				log.error("refreshToken Exception", e);
			}
			
		} 
		
		return null;
	}
	
	@PostMapping("/updateUser")
	public ApiResponse<JwtResponse> updateUser(HttpServletRequest request, @RequestBody UserInfoRequest userInfoReq) throws BizException, CloneNotSupportedException {
		log.debug("updateUser start");
		String userPw = userInfoReq.getCurrentPassword();
		User userInfo = jwtUtils.getLoginUserEntity();

		try {
			// 현재 비밀번호 체크
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), userInfoReq.getCurrentPassword()));
		} catch (Exception e) {
			throw new BizException("BackOfficeUserE001", "현재 비밀번호가 정확하지 않습니다");
		}

		User user = (User) userInfo.clone();
		// 신규 비밀번호 체크 및 set
		if (StringUtils.isNotEmpty(userInfoReq.getNewPassword())) {
			user.setUserPw(encoder.encode(userInfoReq.getNewPassword()));
			userPw = userInfoReq.getNewPassword();
		}

		// 사용자 이름 체크 및 set
		if(StringUtils.isNotEmpty(userInfoReq.getUserNm())) {
			user.setUserNm(userInfoReq.getUserNm());
		}
		
		// 이메일 체크 및 set
		if(StringUtils.isNotEmpty(userInfoReq.getEmail())) {
			user.setUserEmailAddr(userInfoReq.getEmail());
		}
		
		// 사용자정보 저장
		userRepository.save(user);
		
		// 토큰 재발행
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userInfo.getUserId(), userPw));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		String refreshToken = jwtUtils.generateRefreshJwtToken(token);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ApiResponse.ok(new JwtResponse(
				userDetails.getUserSeq(), 
				userDetails.getUsername(), 
				userDetails.getUserRealName(),
				userDetails.getUserEmailAddr(), 
				 roles,
				 token, refreshToken));
	}
	
}
