package com.demo.component;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ironrhino.core.security.role.UserRoleProvider;
import org.springframework.stereotype.Component;

@Component
public class MyUserRoleProvider implements UserRoleProvider {

	@Override
	public Map<String, String> getRoles() {
		Map<String, String> roles = new LinkedHashMap<String, String>();
		roles.put("HR", "人力专员");
		roles.put("MANAGER", "经理");
		return roles;
	}

}
