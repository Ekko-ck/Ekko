package net.openobject.ekko.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	public static final String SYSTEM = "SYSTEM";
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime registeredAt;
	
	@Column
	private String registeredBy;
	
	@LastModifiedDate
	@Column(updatable = true)
	private LocalDateTime modifiedAt;
	
	@Column
	private String modifiedBy;
	
	public void setAuditColumnsToDefault() {
		this.registeredBy = SYSTEM;
		this.modifiedBy = SYSTEM;
	}
}
