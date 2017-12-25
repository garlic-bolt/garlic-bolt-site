package com.chanjetpay.garlic.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by libaoa on 2017/11/9.
 */
public class BlockEnrollForm {
	private String blockName;
	private String wardenEmail;
	private String wardenPhone;

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getWardenEmail() {
		return wardenEmail;
	}

	public void setWardenEmail(String wardenEmail) {
		this.wardenEmail = wardenEmail;
	}

	public String getWardenPhone() {
		return wardenPhone;
	}

	public void setWardenPhone(String wardenPhone) {
		this.wardenPhone = wardenPhone;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}
}
