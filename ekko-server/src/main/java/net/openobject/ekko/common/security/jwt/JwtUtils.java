package net.openobject.ekko.common.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.payload.JwtUserResponse;
import net.openobject.ekko.common.security.service.UserDetailsImpl;

/**
 * JwtUtils.java
 * <br/>
 * JWT 유틸. token을 검증함 
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Slf4j
@Component
public class JwtUtils {

	@Value("${ekkoserver.app.jwt-secret}")
	private String jwtSecret;

	@Value("${ekkoserver.app.jwt-expiration-ms}")
	private int jwtExpirationMs;
	
	@Value("${ekkoserver.app.rjwt-expiration-ms}")
	private int rjwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				//.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	public String generateJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);
		return Jwts.builder().setSubject((username)).setIssuedAt(new Date())
				//.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
	public String generateRefreshJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + rjwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	/**
	 * getLoginUserEntity <br/>
	 * ContextHolder 에서 사용자정보 조회
	 * 
	 * @author  : SeHoon
	 */
	public JwtUserResponse getLoginUserEntity() {
		return (JwtUserResponse)RequestContextHolder.getRequestAttributes().getAttribute("loginUserInfo", RequestAttributes.SCOPE_SESSION);
	}
}
