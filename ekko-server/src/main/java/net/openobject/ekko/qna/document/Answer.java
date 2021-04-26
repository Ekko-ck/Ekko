package net.openobject.ekko.qna.document;

import java.util.List;

import lombok.Data;

@Data
public class Answer {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
    private Long votes;
    private String deleted;
    private String deletedAt;
    private List<Comment> comments;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;
	
}
