package com.demo.action;

import org.ironrhino.core.metadata.Authorize;
import org.ironrhino.core.metadata.AutoConfig;
import org.ironrhino.core.struts.BaseAction;

@AutoConfig(namespace = "/app")
@Authorize(ifAnyGranted = { "MANAGER", "HR" })
public class DemoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		return SUCCESS;
	}

	@Authorize(ifAnyGranted = { "MANAGER" })
	public String test() {
		System.out.println("获取到ID: " + getUid());
		return "test";
	}

}
