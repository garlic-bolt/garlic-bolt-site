package com.chanjetpay.garlic.common;

import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public final class WechatUtil {

	private final static String SITE_PROP_FILE_NAME = "wx-official-account";

	public final static Object initLock = new Object();
	private static ResourceBundle bundle = null;

	static {
		synchronized (initLock) {
			if (bundle == null)
				bundle = ResourceBundle.getBundle(SITE_PROP_FILE_NAME, Locale.CHINA);
		}
	}

	public static String getWxToken() {
		return bundle.getString("wx.token");
	}

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?" +
			"grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 获取access token
	 * @return
	 */
	public static String accessToken() throws IOException {
		String url = ACCESS_TOKEN_URL.replace("APPID",bundle.getString("wx.app.id"))
				.replace("APPSECRET",bundle.getString("wx.app.secret"));

		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		JSONObject json = JSONObject.fromObject(IOUtils.toString(response.getEntity().getContent()));

		if(json.has("errcode")){
			throw new RuntimeException("code:" + json.getString("errcode") + " msg:" + json.getString("errmsg"));
		}

		String accessToken = json.getString("access_token");
		Integer expired = json.getInt("expires_in");

		return accessToken;
	}

	private static final String CALLBACK_IPS_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

	public static List<String> callbackIps(String accessToken) throws IOException {
		String url = ACCESS_TOKEN_URL.replace("ACCESS_TOKEN",accessToken);

		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		JSONObject json = JSONObject.fromObject(IOUtils.toString(response.getEntity().getContent()));

		if(json.has("errcode")){
			throw new RuntimeException("code:" + json.getString("errcode") + " msg:" + json.getString("errmsg"));
		}

		List<String> result = json.getJSONArray("ip_list");

		return result;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(callbackIps("nRHPEH7tyi5LZhBZR8rAlMhLsfYMcG5dz4F5hSFnfuK6EZHL_zSfIQKyBDxj2A00gk1SeziQO2LPN_owu9BN5DPbAKthL96KhtbgPur26tZnV5aLSpoSttG_OqVFkgfzZWXiABALIJ"));
	}

	public static Map<String, String> xml2Map(String xml) throws IOException {
		Map<String, String> map = new HashMap<String, String>();

		SAXReader reader = new SAXReader();

		Document doc = null;
		try {
			doc = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			Element root = doc.getRootElement();

			List<Element> list = root.elements();

			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}

			return map;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 文本消息转xml
	 * @param textMessage
	 * @return
	 */
	public static String text2Xml(TextMessage textMessage){
		XStream xstream = SerializeXmlUtil.createXstream();
		xstream.processAnnotations(TextMessage.class);
		xstream.alias("xml",TextMessage.class);
		return xstream.toXML(textMessage);
	}

	/**
	 * 图文消息转xml
	 * @param newsMessage
	 * @return
	 */
	public static String news2Xml(NewsMessage newsMessage){
		XStream xstream = SerializeXmlUtil.createXstream();
		xstream.processAnnotations(TextMessage.class);
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
}

