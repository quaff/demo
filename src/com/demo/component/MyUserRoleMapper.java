package com.demo.component;

import org.ironrhino.core.security.role.UserRoleMapper;
import org.ironrhino.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyUserRoleMapper implements UserRoleMapper {

	@Override
	public String[] map(UserDetails user) {
		if (user instanceof User) {
			User u = (User) user;
			if (u.getDepartment() != null)
				return new String[] { "ROLE_DEPARTMENT_" + u.getDepartment().getId() };
		}
		return null;
	}

}
