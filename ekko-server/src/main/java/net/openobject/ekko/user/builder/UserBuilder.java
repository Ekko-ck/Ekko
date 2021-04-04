package net.openobject.ekko.user.builder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.openobject.ekko.user.dto.UserInfoResponse;
import net.openobject.ekko.user.entity.User;

/**
 * UserBuilder.java
 * <br/>
 * 사용자 관련 builder
 * 
 * @author  : SeHoon
 * @version : 1.0
 */
@Component
public class UserBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserInfoResponse buildDto(User entity) {
		if (entity == null) {
			return null;
		}
		UserInfoResponse dto = modelMapper.map(entity, UserInfoResponse.class);
		return dto;
	}
	
}
