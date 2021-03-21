package net.openobject.ekko.common.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BaseDto {
	
	@JsonIgnore
	private LocalDateTime registeredAt;
	@JsonIgnore
	private String registeredBy;
	@JsonIgnore
	private LocalDateTime modifiedAt;
	@JsonIgnore
	private String modifiedBy;

}
