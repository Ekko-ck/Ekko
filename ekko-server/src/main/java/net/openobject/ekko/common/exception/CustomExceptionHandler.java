package net.openobject.ekko.common.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	
	private static final long serialVersionUID = -5428374799410131357L;
	
	@Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public final ApiResponse<?> handleBizException(BizException ex, WebRequest request) {
    	String code = ex.getCode().getCode();
    	String message = messageSource.getMessage(code, ex.getParams(), LocaleContextHolder.getLocale());
    	
    	if (StringUtils.isEmpty(message)) {
    		message = ex.getMessage();
    	}
    	
    	log.error("BizException code: {}, messge: {}", code, message, ex);
    	
        return ApiResponse.fail(ex.getCode(), message);
    }

}
