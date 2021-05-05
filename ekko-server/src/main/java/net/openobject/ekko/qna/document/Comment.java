package net.openobject.ekko.qna.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.openobject.ekko.common.entity.BaseDocument;

@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseDocument {
	
    private String userId;
    private String userName;
    private String contents;
    
}
