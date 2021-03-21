package net.openobject.ekko.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.openobject.ekko.common.dto.BaseDto;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SampleUserResponse extends BaseDto {
	
	private Long id;
	private String userId;
	private String userName;
	
}
