package net.openobject.ekko.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.response.ApiResponse;

/**
 * CustomExceptionHandler.java
 * <br/>
 * exception handler
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends Exception {
	
	private static final long serialVersionUID = 1L;

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public final ApiResponse<?> handleBizException(BizException ex, WebRequest request) {
    	log.debug("BizException", ex);
        return ApiResponse.fail(ex.getCode(), ex.getMessage());
    }
 
}
