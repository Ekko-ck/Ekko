package net.openobject.ekko.qna.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "코멘트 등록 Form", description = "코멘트 등록 Form")
@Data
public class CommentRegistrationReuqest {
	
	@ApiModelProperty(value = "내용", required = true)
	private String contents;
	
}
