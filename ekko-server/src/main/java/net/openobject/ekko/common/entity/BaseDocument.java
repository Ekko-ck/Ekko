package net.openobject.ekko.common.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
public class BaseDocument {
	
	@Field(type = FieldType.Text, name = "registeredAt")
	private String registeredAt;
	
	@Field(type = FieldType.Text, name = "registeredBy")
	private String registeredBy;
	
	@Field(type = FieldType.Text, name = "modifiedAt")
	private String modifiedAt;
	
	@Field(type = FieldType.Text, name = "modifiedBy")
	private String 	modifiedBy;
	
	public void setForRegistration(String userId) {
		LocalDateTime ldt = LocalDateTime.now();
		String nowDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.registeredAt = nowDate;
		this.registeredBy = userId;
		this.modifiedAt = nowDate;
		this.modifiedBy = userId;
	}
	
	public void setForModification(String userId) {
		LocalDateTime ldt = LocalDateTime.now();
		String nowDate = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.modifiedAt = nowDate;
		this.modifiedBy = userId;
	}
}
