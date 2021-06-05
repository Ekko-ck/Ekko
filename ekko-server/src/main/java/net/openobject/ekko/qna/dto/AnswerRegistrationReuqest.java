package net.openobject.ekko.qna.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "답변 등록 Form", description = "답변 등록 Form")
@Data
public class AnswerRegistrationReuqest {
	
	@ApiModelProperty(value = "내용", required = true)
	private String contents;
	
}
