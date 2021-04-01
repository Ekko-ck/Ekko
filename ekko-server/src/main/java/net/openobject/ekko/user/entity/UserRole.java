package net.openobject.ekko.user.entity;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

/**
 * UserRole.java <br/>
 * 사용자 권한 테이블 도메인
 * 
 * @author : SeHoon
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "TB_USER_ROLE")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RULE_SEQ")
	private long ruleSeq;

	@Enumerated(EnumType.STRING)
    @Column(name = "RULE_NAME", nullable = false, columnDefinition = "enum('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
	@ColumnDefault("'ROLE_USER'")
	private UserERole name;
	
	public UserRole() {
	}
	
	public UserRole(long ruleSeq, UserERole name) {
		this.ruleSeq = ruleSeq;
		this.name = name;
	}

	public UserRole ruleSeq(long ruleSeq) {
		this.ruleSeq = ruleSeq;
		return this;
	}

	public UserRole name(UserERole name) {
		this.name = name;
		return this;
	}

}