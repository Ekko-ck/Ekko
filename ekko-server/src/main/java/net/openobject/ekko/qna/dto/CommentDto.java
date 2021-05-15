package net.openobject.ekko.qna.dto;

import lombok.Data;

@Data
public class CommentDto {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
	private String registeredAt;
	private String registeredBy;
	private String modifiedAt;
	private String 	modifiedBy;
	
}
