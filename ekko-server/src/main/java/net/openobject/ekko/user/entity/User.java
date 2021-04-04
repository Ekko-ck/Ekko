package net.openobject.ekko.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.openobject.ekko.common.entity.BaseEntity;
import net.openobject.ekko.user.dto.UserInfoRequest;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "TB_USER", uniqueConstraints = { @UniqueConstraint(columnNames = "USER_ID") })
public class User extends BaseEntity implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SEQ")
	private Long userSeq;

	@Column(name = "USER_ID", length = 20, nullable = false)
	private String userId;

	@Column(name = "USER_PW", length = 255, nullable = false)
	private String userPw;

	@Column(name = "USER_NM", length = 10, nullable = false)
	private String userNm;

	@Column(name = "USER_EMAIL_ADDR", length = 50, nullable = false)
	private String userEmailAddr;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USER_ROLES", joinColumns = @JoinColumn(name = "USER_SEQ"), inverseJoinColumns = @JoinColumn(name = "RULE_SEQ"))
	private Set<UserRole> roles = new HashSet<>();

	public User(String userId, String userPw, String userNm, String userEmailAddr) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNm = userNm;
		this.userEmailAddr = userEmailAddr;
	}
	
	public User(String userId, String userPw, String userNm, String userEmailAddr, Set<UserRole> roles) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNm = userNm;
		this.userEmailAddr = userEmailAddr;
		this.roles = roles;
	}
	
	public User id(Long id) {
		this.userSeq = id;
		return this;
	}

	public User username(String username) {
		this.userId = username;
		return this;
	}

	public User email(String email) {
		this.userEmailAddr = email;
		return this;
	}

	public User password(String password) {
		this.userPw = password;
		return this;
	}

	public User roles(Set<UserRole> roles) {
		this.roles = roles;
		return this;
	}

	public User update(UserInfoRequest formDto) {
		this.userNm = formDto.getUserNm();
		this.userPw = formDto.getNewPassword();
		this.userEmailAddr = formDto.getUserEmailAddr();
		return this;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
