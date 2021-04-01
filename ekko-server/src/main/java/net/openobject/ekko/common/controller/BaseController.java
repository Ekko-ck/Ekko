package net.openobject.ekko.common.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.constants.GeneralConstants;
import net.openobject.ekko.common.exception.BizException;

/**
 * BaseController.java
 * <br/>
 * 공통 controller
 * 컨트롤러에서 공통적으로 사용하는 함수 정의
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Slf4j
public class BaseController {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> DefaultException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		log.error("DefaultException", e);
        JSONObject json = getResponseJson(GeneralConstants.RESULT_CODE_FAIL, e.getMessage(), new HashMap<>());
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<String> BizExceptionHand(HttpServletRequest request, HttpServletResponse response, BizException e) {
    	log.error("BizExceptionHand", e);
        JSONObject json = getResponseJson(e.getCode(), e.getMessage(), new HashMap<>());
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}

	protected ResponseEntity<String> getResponseEntity(Object bodyObject){
		JSONObject json = getResponseJson(GeneralConstants.RESULT_CODE_SUCCESS, GeneralConstants.RESULT_MESSAGE_SUCCESS, bodyObject);

		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}

	private JSONObject getResponseJson(String resultCode, String resultMessage, Object bodyObject) {
		JSONObject responsJson = new JSONObject();
		JSONObject headJson = new JSONObject();
		
		try {
			headJson.put(GeneralConstants.API_HEAD_RESULT_CODE, resultCode);
			headJson.put(GeneralConstants.API_HEAD_RESULT_MESSAGE, resultMessage);
			
			responsJson.put(GeneralConstants.API_HEAD, headJson);
			responsJson.put(GeneralConstants.API_BODY, new JSONObject(new ObjectMapper().writeValueAsString(bodyObject)));
			
		}catch(JSONException | JsonProcessingException ex) {
			log.error("getResponseJson exception", ex);
		}
		
		return responsJson;
	}
	
	
}
