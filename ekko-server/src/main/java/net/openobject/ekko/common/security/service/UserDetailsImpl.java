package net.openobject.ekko.common.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.openobject.ekko.common.auth.payload.JwtUserResponse;
import net.openobject.ekko.user.entity.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userSeq;
	private String userId;
	private String userEmailAddr;
	private String userRealName;

	@JsonIgnore
	private String userPw;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long userSeq, String userId, String userEmailAddr, String userPw, String userRealName,
			Collection<? extends GrantedAuthority> authorities) {
		this.userSeq = userSeq;
		this.userId = userId;
		this.userEmailAddr = userEmailAddr;
		this.userPw = userPw;
		this.userRealName = userRealName;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getUserSeq(), user.getUserId(), user.getUserEmailAddr(),
				user.getUserPw(), user.getUserNm(), authorities);
	}

	public static UserDetailsImpl build(JwtUserResponse user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getUserSeq(), user.getUserId(), user.getUserEmailAddr(),
				user.getUserPw(), user.getUserNm(), authorities);
	}
	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserEmailAddr() {
		return userEmailAddr;
	}

	public void setUserEmailAddr(String userEmailAddr) {
		this.userEmailAddr = userEmailAddr;
	}

    @Override
    public String getPassword() {
        return userPw;
    }
 
    @Override
    public String getUsername() {
        return userId;
    }
    
	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
