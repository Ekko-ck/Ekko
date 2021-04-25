package net.openobject.ekko.common.response;

import lombok.Getter;

@Getter
public enum ResultCode {
	SUCCESS("0000"),
	SERVER_ERROR("9999"),
	;
	
	private String code;
	ResultCode(String code) {
		this.code = code;
	}
}
