package net.openobject.ekko.common.auth.payload;
import lombok.Data;

@Data
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
	    this.message = message;
	}

}
