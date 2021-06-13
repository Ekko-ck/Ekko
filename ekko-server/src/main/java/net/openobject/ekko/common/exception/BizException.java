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
public class BizException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Server Error!";

	private ResultCode code = ResultCode.SERVER_ERROR;
	private Object[] params = null;
	
	public BizException() {
		super(DEFAULT_MESSAGE, null);
	}
	
	public BizException(ResultCode code) {
		super(DEFAULT_MESSAGE, null);
		this.code = code;
	}
	
	public BizException(ResultCode code, Object[] params) {
		super(DEFAULT_MESSAGE, null);
		this.code = code;
		this.params = params;
	}
	
	public BizException(ResultCode code, String message) {
		super(message, null);
		this.code = code;
	}
	
	public BizException(ResultCode code, String message, Object[] params) {
		super(message, null);
		this.code = code;
		this.params = params;
	}
	
	public BizException(ResultCode code, String message, Throwable t) {
		super(message, t);
		this.code = code;
	}
	
	public BizException(ResultCode code, String message, Throwable t, Object[] params) {
		super(message, t);
		this.code = code;
		this.params = params;
	}
	
	public ResultCode getCode() {
		return this.code;
	}
	
	public Object[] getParams() {
		return this.params;
	}

}
