package net.openobject.ekko.common.exception;

import net.openobject.ekko.common.response.ResultCode;

/**
 * BizException.java
 * <br/>
 * 비즈니스 exception 처리
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
public class BizException extends Exception{

	private static final long serialVersionUID = 1L;

	private ResultCode code = ResultCode.SERVER_ERROR;
	
	public BizException() {
		super("errorMessage", null);
	}
	
	public BizException(ResultCode code) {
		super("errorMessage", null);
		this.code = code;
	}
	
	public BizException(ResultCode code, String message) {
		super(message, null);
		this.code = code;
	}
	
	public BizException(ResultCode code, String message, Throwable t) {
		super(message, t);
		this.code = code;
	}
	
	public ResultCode getCode() {
		return this.code;
	}

}
