package net.openobject.ekko.user.dto;

import lombok.Data;

/**
 * UserInfoReq.java
 * <br/>
 * 사용자 정보 요청 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class UserInfoRequest {
	private String currentPassword;			// 현재 페스워드
	private String newPassword;				// 신규 페스워크
	private String userNm;					// 사용자명
	private String email;					// 이메일
}
