package net.openobject.ekko.qna.dto;

import java.util.List;

import lombok.Data;
import net.openobject.ekko.qna.document.Answer;
import net.openobject.ekko.qna.document.AttachedFile;
import net.openobject.ekko.qna.document.Comment;

@Data
public class QuestionDto {
	
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
	private List<Answer> answers;
	private List<Comment> comments;
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String 	modifiedBy;
	
}
