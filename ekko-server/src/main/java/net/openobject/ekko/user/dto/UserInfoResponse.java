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
public class UserInfoResponse {
	private String currentPassword;			// 현재 페스워드
	private String newPassword;				// 신규 페스워크
	private String userNm;					// 사용자명
	private String userEmailAddr;			// 이메일
	private String userId;					// 사용자ID
	private Long userSeq;					// 사용자seq
}
