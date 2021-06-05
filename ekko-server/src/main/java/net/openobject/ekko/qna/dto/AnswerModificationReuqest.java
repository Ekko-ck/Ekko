package net.openobject.ekko.qna.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "답변 수정 Form", description = "답변 수정 Form")
@Data
public class AnswerModificationReuqest {
	
	@ApiModelProperty(value = "내용", required = true)
	private String contents;
	
}
