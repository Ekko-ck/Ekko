package net.openobject.ekko.qna.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "질문 수정 Form", description = "질문 수정 Form")
@Data
public class QuestionModificationReuqest {
	
	@ApiModelProperty(value = "제목", required = true)
	private String title;
	@ApiModelProperty(value = "내용", required = true)
	private String contents;
	@ApiModelProperty(value = "태그")
	private List<String> tags;
	
}
