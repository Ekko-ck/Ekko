package net.openobject.ekko.qna.document;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.entity.BaseDocument;
import net.openobject.ekko.common.util.IndentityUtil;

@Data
@EqualsAndHashCode(callSuper = false)
public class Answer extends BaseDocument {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
    private Long votes;
    private Boolean deleted;
    private String deletedAt;
    private List<Comment> comments;
    
	public void init(JwtUserResponse user) {
		this.id = IndentityUtil.randomUuid();
		this.votes = 0L;
		this.deleted = false;
		this.userId = user.getUserId();
		this.userName = user.getUserNm();
		this.setForRegistration(user.getUserId());
	}
	
	public boolean isSameUser(String checkUserId) {
		return this.userId.equals(checkUserId);
	}
	
	public void addComment(Comment comment) {
		if (this.comments == null) {
			this.comments = new ArrayList<>();
		}
		this.comments.add(comment);
	}
	
}
