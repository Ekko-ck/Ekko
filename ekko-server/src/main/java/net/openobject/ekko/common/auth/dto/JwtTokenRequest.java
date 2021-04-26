package net.openobject.ekko.common.auth.dto;

import lombok.Data;

/**
 * JwtTokenReq.java
 * <br/>
 * 토큰정보 요청 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class JwtTokenRequest {
	private String token;
	private String refreshtoken;
}
