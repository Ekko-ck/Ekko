package net.openobject.ekko.common.response;

import lombok.Getter;

@Getter
public enum ResultCode {
	SUCCESS("0000"),
	SERVER_ERROR("9999"),
	BAD_REQUEST("0400"),
	UNAUTHORIZED("0401"),
	FORBIDDEN("0403"),
	NOT_FOUND("0404"),
	;
	
	private String code;
	ResultCode(String code) {
		this.code = code;
	}
}
