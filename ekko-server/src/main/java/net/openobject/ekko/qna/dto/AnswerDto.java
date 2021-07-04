package net.openobject.ekko.qna.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnswerDto implements Modifiable {
	
	private String questionId;
	private String id;
    private String userId;
    private String userName;
    private String contents;
    private Long votes;
    private String deleted;
    private String deletedAt;
    private List<CommentDto> comments;
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
