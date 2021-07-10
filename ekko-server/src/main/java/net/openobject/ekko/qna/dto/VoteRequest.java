package net.openobject.ekko.qna.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.openobject.ekko.qna.entity.VoteSourceType;

@ApiModel(value = "질문 등록 Form", description = "질문 등록 Form")
@Data
public class VoteRequest {
	
	@ApiModelProperty(value = "질문 or 답변 ID", required = true)
	private String sourceId;
	@ApiModelProperty(value = "투표 UP or DOWN", required = true, allowableValues = "UP, DOWN")
	private VoteValue voteValue;
	@ApiModelProperty(hidden = true)
	private VoteSourceType sourceType;
	
}
