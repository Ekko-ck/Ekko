package net.openobject.ekko.common.auth.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * LoginRequest.java
 * <br/>
 * 로그인 요청 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class LoginRequest {
	@NotBlank
	private String userId;

	@NotBlank
	private String password;
	
}
