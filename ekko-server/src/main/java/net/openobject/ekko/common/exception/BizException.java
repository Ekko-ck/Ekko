package net.openobject.ekko.common.exception;

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

	private String code = "";
	
	public BizException() {
		super("errorMessage", null);
	}
	
	public BizException(String code) {
		super("errorMessage", null);
		this.code = code;
	}
	
	public BizException(String code, String message) {
		super(message, null);
		this.code = code;
	}
	
	public BizException(String code, String message, Throwable t) {
		super(message, t);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}

}
