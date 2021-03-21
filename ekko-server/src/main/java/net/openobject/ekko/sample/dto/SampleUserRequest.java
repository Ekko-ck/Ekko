package net.openobject.ekko.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleUserRequest {
	
	private Long id;
	private String userId;
	private String userName;
	
}
