package net.openobject.ekko.common.auth.dto;

import java.util.Set;

import lombok.Data;
import net.openobject.ekko.user.entity.UserRole;

/**
 * JwtUserResponse.java
 * <br/>
 * jwt 사용자 응답 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class JwtUserResponse {
	private String userPw;				// 신규 페스워크
	private String userNm;					// 사용자명
	private String userEmailAddr;			// 이메일
	private String userId;					// 사용자ID
	private Long userSeq;					// 사용자seq
	private Set<UserRole> roles;
}
