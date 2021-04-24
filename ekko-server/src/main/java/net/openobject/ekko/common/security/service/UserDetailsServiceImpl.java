package net.openobject.ekko.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import net.openobject.ekko.common.auth.payload.JwtUserResponse;
import net.openobject.ekko.user.repository.UserRepository;
import net.openobject.ekko.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		/*
		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));
		*/
		JwtUserResponse user = userService.findByUserId(userId);
		
		RequestContextHolder.getRequestAttributes().setAttribute("loginUserInfo", user, RequestAttributes.SCOPE_SESSION);
		
		return UserDetailsImpl.build(user);
	}

}
