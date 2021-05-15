package net.openobject.ekko.qna.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.entity.BaseDocument;
import net.openobject.ekko.qna.dto.AttachedFile;

@Document(indexName = "question")
@Data
@EqualsAndHashCode(callSuper = false)
public class Question extends BaseDocument {
	
	@Id
	private String id;
	
	@Field(type = FieldType.Text, name = "userId")
	private String userId;
	
	@Field(type = FieldType.Text, name = "userName")
	private String userName;
	
	@Field(type = FieldType.Text, name = "title")
	private String title;
	
	@Field(type = FieldType.Text, name = "contents")
	private String contents;
	
	@Field(type = FieldType.Long, name = "votes")
	private Long votes;
	
	@Field(type = FieldType.Long, name = "views")
	private Long views;
	
	@Field(type = FieldType.Boolean, name = "deleted")
	private Boolean deleted;
	
	@Field(type = FieldType.Text, name = "deletedAt")
	private String deletedAt;
	
	@Field(type = FieldType.Auto, name = "attched")
	private List<AttachedFile> attched;
	
	@Field(type = FieldType.Auto, name = "tags")
	private List<String> tags;
	
	@Field(type = FieldType.Object, name = "answers")
	private List<Answer> answers;
	
	@Field(type = FieldType.Object, name = "comments")
	private List<Comment> comments;
	
	public void init(JwtUserResponse user) {
		this.votes = 0L;
		this.views = 0L;
		this.deleted = false;
		this.userId = user.getUserId();
		this.userName = user.getUserNm();
		this.setForRegistration(user.getUserId());
	}
	
	public boolean isSameUser(String checkUserId) {
		return this.userId.equals(checkUserId);
	}
	
	public void addAnswer(Answer answer) {
		if (this.answers == null) {
			this.answers = new ArrayList<>();
		}
		this.answers.add(answer);
	}
	
	public void addComment(Comment comment) {
		if (this.comments == null) {
			this.comments = new ArrayList<>();
		}
		this.comments.add(comment);
	}
	
}
