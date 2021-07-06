package net.openobject.ekko.qna.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionDto implements Modifiable {
	
	private String id;
	private String userId;
	private String userName;
	private String title;
	private String contents;
	private Long votes;
	private Long views;
	private Boolean deleted;
	private String deletedAt;
	private List<AttachedFile> attched;
	private List<String> tags;
	private List<AnswerDto> answers;
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
