package net.openobject.ekko.common.auth.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank
	private String userId;

	@NotBlank
	private String password;
	
}
