package net.openobject.ekko.qna.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "question")
@Data
public class Question {
	
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
	
	@Field(type = FieldType.Object, name = "attched")
	private List<AttachedFile> attched;
	
	@Field(type = FieldType.Object, name = "tags")
	private List<String> tags;
	
	@Field(type = FieldType.Object, name = "tags")
	private List<Answer> answers;
	
	@Field(type = FieldType.Object, name = "tags")
	private List<Comment> comments;
	
	@Field(type = FieldType.Text, name = "createdAt")
	private String createdAt;
	
	@Field(type = FieldType.Text, name = "createdBy")
	private String createdBy;
	
	@Field(type = FieldType.Text, name = "modifiedAt")
	private String modifiedAt;
	
	@Field(type = FieldType.Text, name = "modifiedBy")
	private String 	modifiedBy;
	
}
