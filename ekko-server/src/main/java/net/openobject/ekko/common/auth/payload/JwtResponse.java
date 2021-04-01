package net.openobject.ekko.common.auth.payload;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String type = "Bearer";
	private Long id;
	private String userId;
	private String userNm;
	private String email;
	private List<String> roles;
	private String token;
	private String refreshToken;

	public JwtResponse(Long id, String userId, String userNm, String email, List<String> roles, String token, String refreshToken) {
		super();
		this.id = id;
		this.userId = userId;
		this.userNm = userNm;
		this.email = email;
		this.roles = roles;
		this.token = token;
		this.refreshToken = refreshToken;
	}

}
