package net.openobject.ekko.common.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "공통응답", description = "공통응답")
@Slf4j
@Getter
@ToString
public class ApiResponse<T> {
	
	@ApiModelProperty(value = "응답코드")
	private ResultCode resultCode;
	@ApiModelProperty(value = "응답메세지")
	private String resultMessage;
	@ApiModelProperty(value = "응답시간")
	private LocalDateTime serverTime;
	@ApiModelProperty(value = "데이터")
	private T data;
	
	private ApiResponse() {
		setServerTimeToNow();
	}
	
	private ApiResponse(ResultCode resultCode) {
		this.resultCode = resultCode;
		setServerTimeToNow();
	}
	
	private ApiResponse(T data) {
		this.data = data;
		setServerTimeToNow();
	}
	
	private ApiResponse(ResultCode resultCode, T data) {
		this.resultCode = resultCode;
		this.data = data;
		setServerTimeToNow();
	}
	
	private void setServerTimeToNow() {
		this.serverTime = LocalDateTime.now();
	}
	
	public static <T> ApiResponse<T> ok() {
		return new ApiResponse<T>(ResultCode.SUCCESS);
	}
	
	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<T>(ResultCode.SUCCESS, data);
	}
	
	public static <T> ApiResponse<T> make(ResultCode resultCode) {
		return new ApiResponse<T>(resultCode);
	}
	
	public static <T> ApiResponse<T> make(ResultCode resultCode, T data) {
		return new ApiResponse<T>(resultCode, data);
	}
	
	public static <T> ApiResponse<T> fail() {
		return new ApiResponse<T>(ResultCode.SERVER_ERROR);
	}
	
	public static <T> ApiResponse<T> fail(ResultCode resultCode, T data) {
		return new ApiResponse<T>(resultCode, data);
	}
	
	public static <T> ApiResponse<T> fail(T data) {
		return new ApiResponse<T>(ResultCode.SERVER_ERROR, data);
	}
	
	public String getResultCode() {
		if (this.resultCode == null) {
			log.error("resultCode is null!");
			return ResultCode.SERVER_ERROR.getCode();
		}
		return this.resultCode.getCode();
	}
	
}
