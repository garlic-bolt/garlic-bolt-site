package com.chanjetpay.garlic.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/***
 * 文本消息
 */
public class TextMessage extends BaseMessage {

	@XStreamCDATA
	@XStreamAlias("Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
