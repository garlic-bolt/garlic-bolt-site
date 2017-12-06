package com.chanjetpay.garlic.common;

/**
 * 微信消息类型
 */
public class MessageType {

	//消息类型：文本
	public static final String MESSAGE_TYPE_TEXT = "text";
	//消息类型：图片
	public static final String MESSAGE_TYPE_IMAGE="image";
	//消息类型：语音
	public static final String MESSAGE_TYPE_VOICE="voice";
	//消息类型：视频
	public static final String MESSAGE_TYPE_VIDEO="video";
	//消息类型：小视频
	public static final String MESSAGE_TYPE_SHORTVIDEO="shortvideo";
	//消息类型：地理位置
	public static final String MESSAGE_TYPE_LOCATION="location";
	//消息类型：链接
	public static final String MESSAGE_TYPE_LINK="link";
	//消息类型：音乐
	public static final String MESSAGE_TYPE_MUSIC="music";
	//消息类型：图文
	public static final String MESSAGE_TYPE_NEWS="news";

	//消息类型：事件推送
	public static final String MESSAGE_TYPE_EVENT="event";
	//事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE="subscribe";
	//事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE="unsubscribe";
	//事件类型：scan(用户已关注时的扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN="scan";
	//事件类型：LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION="LOCATION";
	//事件类型：CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK="CLICK";
	//事件类型：自定义菜单
	public static final String EVENT_TYPE_VIEW="VIEW";
}
