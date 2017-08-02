package com.demo.enums;

import org.ironrhino.core.model.Displayable;

public enum Status implements Displayable {

	NORMAL, LOW_INVENTORY, SOLDOUT;

	@Override
	public String toString() {
		return getDisplayName();
	}

}
