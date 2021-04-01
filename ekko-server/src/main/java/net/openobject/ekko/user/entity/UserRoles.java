package net.openobject.ekko.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * UserRoles.java <br/>
 * 사용자 권한 테이블 도메인
 * 
 * @author : jiyong
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "TB_USER_ROLES")
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SEQ")
	private long userSeq;
	
	@Column(name = "RULE_SEQ")
	private long ruleSeq;
	public UserRoles() {
		
	}

}