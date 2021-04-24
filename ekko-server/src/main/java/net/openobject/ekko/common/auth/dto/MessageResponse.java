package net.openobject.ekko.common.auth.dto;
import lombok.Data;

/**
 * MessageResponse.java
 * <br/>
 * 메세지 응답 VO
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	}

}
