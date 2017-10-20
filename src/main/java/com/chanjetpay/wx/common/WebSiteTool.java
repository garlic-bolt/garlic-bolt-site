package com.chanjetpay.wx.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by libaoa on 2017/10/20.
 */
public final class WebSiteTool {

	private final static String SITE_PROP_FILE_NAME = "app";

	public final static Object initLock = new Object();
	private static ResourceBundle bundle = null;

	static {
		synchronized (initLock) {
			if (bundle == null)
				bundle = ResourceBundle.getBundle(SITE_PROP_FILE_NAME, Locale.CHINA);
		}
	}

	public static String getConfigValue(String key){
		return bundle.getString(key);
	}

	/**
	 * 获取当前Url
	 * @return wbsite url
	 */
	public String getSiteUrl(){
		return bundle.getString("website.url");
	}

}
