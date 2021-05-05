package net.openobject.ekko.qna.document;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.openobject.ekko.common.entity.BaseDocument;

@Data
@EqualsAndHashCode(callSuper = false)
public class Answer extends BaseDocument {
	
	private String id;
    private String userId;
    private String userName;
    private String contents;
    private Long votes;
    private String deleted;
    private String deletedAt;
    private List<Comment> comments;
	
}
