package net.openobject.ekko.qna.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.entity.BaseDocument;
import net.openobject.ekko.common.util.IndentityUtil;

@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseDocument {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
    
	public void init(JwtUserResponse user) {
		this.id = IndentityUtil.randomUuid();
		this.userId = user.getUserId();
		this.userName = user.getUserNm();
		this.setForRegistration(user.getUserId());
	}
	
	public boolean isSameUser(String checkUserId) {
		return this.userId.equals(checkUserId);
	}
    
}
