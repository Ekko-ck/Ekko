package net.openobject.ekko.sample.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "샘플사용자정보", description = "샘플사용자정보")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleUserRequest {
	
	@ApiModelProperty(value = "사용자시퀀스", required = true)
	private Long id;
	
	@ApiModelProperty(value = "사용자ID", required = true)
	private String userId;
	
	@ApiModelProperty(value = "사용자명")
	private String userName;
	
}
