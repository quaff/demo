package com.demo.service;

import org.ironrhino.security.service.UserManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.demo.domain.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserManager userManager;

	@Override
	public boolean accepts(String username) {
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		if (username == null)
			throw new IllegalArgumentException("username shouldn't be null");
		UserDetails u = userManager.loadUserByUsername(username);
		if (u == null)
			return null;
		User user = new User();
		BeanUtils.copyProperties(u, user);
		return user;
	}

}
