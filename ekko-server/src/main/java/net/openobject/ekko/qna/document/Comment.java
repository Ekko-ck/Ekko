package net.openobject.ekko.qna.document;

import lombok.Data;

@Data
public class Comment {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;
    
}
