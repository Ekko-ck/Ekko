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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtResponse;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.auth.dto.LoginRequest;
import net.openobject.ekko.common.auth.dto.MessageResponse;
import net.openobject.ekko.common.auth.dto.SignupRequest;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.common.response.ResultCode;
import net.openobject.ekko.common.security.jwt.JwtUtils;
import net.openobject.ekko.common.security.service.UserDetailsImpl;
import net.openobject.ekko.user.dto.RefreshtokenResponse;
import net.openobject.ekko.user.dto.UserInfoRequest;
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
	private JwtUtils jwtUtils;
	
	@Value("${ekkoserver.app.jwt-expiration-ms}") //AccessToken 유효시간 30분
	private int jwtExpirationMs;
	/**
	 * authenticateUser(/api/user/auth/signin)
	 * <br/>
	 * 로그인
	 * 
	 * @author  : SeHoon
	 * @throws BizException 
	 */
	@PostMapping("/auth/signin")
	public ApiResponse<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest,HttpServletRequest request) throws BizException {
		
		JwtResponse result = new JwtResponse();
		try {
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
		
			/** 4. 응답값 set **/
			result = new JwtResponse(userDetails.getUserSeq(), userDetails.getUsername(), 
					userDetails.getUserRealName(),userDetails.getUserEmailAddr(), roles,
					 token, refreshToken);
		} catch (Exception e) {
			log.error("signin bizException ", e);
			throw new BizException(ResultCode.SERVER_ERROR, "계정이 없거나 비밀번호가 맞지 않습니다");
		}
		
		return ApiResponse.ok(result);
	}

	/**
	 * registerUser(/api/user/auth/signup) <br/>
	 * 사용자등록
	 * 
	 * @author : SeHoon
	 * @throws BizException
	 */
	@PostMapping("/auth/signup")
	public ApiResponse<MessageResponse> registerUser(@RequestBody SignupRequest signUpReq) throws BizException {
		
		/** 1. 사용자 ID 존재여부 체크 **/
		if (userService.existsByUserId(signUpReq.getUserId())) {
			throw new BizException(ResultCode.SERVER_ERROR, "UserId : '"+signUpReq.getUserId()+"' 는 이미 존재합니다.");
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
	 * refreshToken(/api/user/auth/refreshtoken)
	 * 
	 * 사용자 토큰 재발급 
	 * 
	 * @author SeHoon
	 * @throws BizException 
	 * @throws IOException 
	 */
	@GetMapping(value = "/auth/refreshtoken")
	public ApiResponse<RefreshtokenResponse> refreshToken(HttpServletRequest request) throws BizException {
		
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
				}
			} catch (Exception e) {
				log.error("refreshtoken Exception ", e);
			}
		}
		
		if(StringUtil.isNullOrEmpty(newToken)) {
			throw new BizException(ResultCode.SERVER_ERROR, "자동로그아웃 되었습니다. 다시 로그인해주세요");
		}
		
		/** 응답값 set **/
		RefreshtokenResponse result = new RefreshtokenResponse(newToken, newRefreshToken);
		
		return ApiResponse.ok(result);
	}
	
	/**
	 * refreshToken(/api/user/auth/modifyUser)
	 * 
	 * 사용자 정보 변경 
	 * 
	 * @author SeHoon
	 * @throws IOException 
	 */
	@PostMapping("/auth/modifyUser")
	public ApiResponse<JwtResponse> modifyUser(HttpServletRequest request, @RequestBody UserInfoRequest userInfoReq) throws BizException, CloneNotSupportedException {
		
		String newPassword = userInfoReq.getNewPassword();
		try {
			/** 1. 현재 비밀번호 체크 **/
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfoReq.getUserId(), userInfoReq.getCurrentPassword()));
		} catch (Exception e) {
			throw new BizException(ResultCode.SERVER_ERROR, "현재 비밀번호가 정확하지 않습니다");
			//return ApiResponse.fail();
		}

		/** 2. 사용자정보 변경 **/
		JwtUserResponse userInfoRes = userService.modifyUser(userInfoReq);
		
		/** 3. JWT토큰 재생성 **/
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfoRes.getUserId(), newPassword));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtUtils.generateJwtToken(authentication);
		String refreshToken = jwtUtils.generateRefreshJwtToken(token);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		/** 4. 응답값 set **/
		JwtResponse result = new JwtResponse(userInfoRes.getUserSeq(), userInfoRes.getUserId(), 
				userInfoRes.getUserNm(), userInfoRes.getUserEmailAddr(), 
				 roles, token, refreshToken);
		
		return ApiResponse.ok(result);
	}
	
	/**
	 * refreshToken(/api/user/refreshtoken)
	 * 
	 * 로그인체크 테스트
	 * 
	 * @author SeHoon
	 * @throws IOException 
	 */
	@PostMapping("/logincheck")
	public ApiResponse<JwtResponse> logincheck(HttpServletRequest request) throws BizException, CloneNotSupportedException {
		
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		String headerAuth = request.getHeader("Authorization");
		headerAuth = headerAuth.substring(7, headerAuth.length());
	
		return ApiResponse.ok(new JwtResponse(
				user.getUserSeq(), 
				user.getUserId(), 
				user.getUserNm(),
				user.getUserEmailAddr(), 
				 null,
				 headerAuth, ""));
	}
	
}
