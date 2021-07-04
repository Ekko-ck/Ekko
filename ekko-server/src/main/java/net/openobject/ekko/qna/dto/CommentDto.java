package net.openobject.ekko.qna.dto;

import lombok.Data;

@Data
public class CommentDto implements Modifiable {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
	private String registeredAt;
	private String registeredBy;
	private String modifiedAt;
	private String 	modifiedBy;
	
	private Boolean isMine;
	
	@Override
	public void setIsMine(String userId) {
		this.isMine = this.userId.equals(userId);
	}
	
}
