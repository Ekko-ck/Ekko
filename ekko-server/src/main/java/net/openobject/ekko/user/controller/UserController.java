package net.openobject.ekko.user.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.common.security.jwt.JwtUtils;
import net.openobject.ekko.common.security.service.UserDetailsImpl;
import net.openobject.ekko.user.dto.RefreshtokenResponse;
import net.openobject.ekko.user.dto.UserInfoRequest;
import net.openobject.ekko.user.entity.User;
import net.openobject.ekko.user.entity.UserERole;
import net.openobject.ekko.user.entity.UserRole;
import net.openobject.ekko.user.service.UserService;

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
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
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

		/** 1. ID/PW 인증 **/
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/** 2. 토큰 생성 **/
		// 2-1 JWT토큰생성
		String token = jwtUtils.generateJwtToken(authentication);
		// 2-2 JWT 리프레시 토큰생성 
		String refreshToken = jwtUtils.generateRefreshJwtToken(token);
		
		//토큰을 발행하고 같은 생명주기의 세션을 생성
		HttpSession session = request.getSession();
		session.setAttribute("userId", loginRequest.getUserId());
		session.setMaxInactiveInterval(jwtExpirationMs/1000);
		
		/** 3. 사용자 상세정보 조회 **/
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
		
		/** 1. 사용자 ID 존재여부 체크 **/
		if (userService.existsByUserId(signUpReq.getUserId())) {
			throw new BizException("signup001", "UserId : '"+signUpReq.getUserId()+"' 는 이미 존재합니다.");
		}

		/** 2. 사용자 권한정보 SET **/
		Set<String> strRoles = signUpReq.getStrRole();
		Set<UserRole> roles = new HashSet<>();

		if (strRoles == null) {
			UserRole userRole = userService.findByName(UserERole.ROLE_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					UserRole adminRole = userService.findByName(UserERole.ROLE_ADMIN);
					roles.add(adminRole);

					break;
				case "mod":
					UserRole modRole = userService.findByName(UserERole.ROLE_MANAGER);
					roles.add(modRole);

					break;
				default:
					UserRole userRole = userService.findByName(UserERole.ROLE_USER);
					roles.add(userRole);
				}
			});
		}

		signUpReq.setRole(roles);
		
		/** 3. 사용자 저장 **/
		userService.registerUser(signUpReq);

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
	public ApiResponse<RefreshtokenResponse> refreshToken(HttpServletRequest request) {
		
		// 인증 헤더정보 GET
		String headerAuth = request.getHeader("Authorization");
		String newToken = "";
		String newRefreshToken = "";
		
		if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
			String refreshToken = headerAuth.substring(7, headerAuth.length());
			try {
				if(jwtUtils.validateJwtToken(refreshToken)) {
					newToken = jwtUtils.generateJwtToken(refreshToken);
					newRefreshToken = jwtUtils.generateRefreshJwtToken(newToken);
					
				} else {
					throw new BizException("refreshtokenE001", "토큰이 유효하지 않습니다");
				}
			} catch (Exception e) {
				log.error("refreshToken Exception", e);
			}
		}
		
		return ApiResponse.ok(new RefreshtokenResponse(newToken, newRefreshToken));
	}
	
	@PostMapping("/updateUser")
	public ApiResponse<JwtResponse> updateUser(HttpServletRequest request, @RequestBody UserInfoRequest userInfoReq) throws BizException, CloneNotSupportedException {
		
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
		userService.registerUser(user);
		
		// 토큰 재발행
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserId(), userPw));
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
