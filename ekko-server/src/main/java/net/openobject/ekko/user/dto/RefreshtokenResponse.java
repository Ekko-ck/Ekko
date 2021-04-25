package net.openobject.ekko.user.dto;

import lombok.Data;

/**
 * RefreshtokenRequest.java
 * <br/>
 * 리프레시토큰 응답 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class RefreshtokenResponse {
	private String token;
	private String refreshToken;
	
	public RefreshtokenResponse(String token, String refreshToken) {
		super();
		this.token = token;
		this.refreshToken = refreshToken;
	}
}
