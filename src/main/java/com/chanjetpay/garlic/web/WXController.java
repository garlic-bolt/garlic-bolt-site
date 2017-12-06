package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.common.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WXController {

	private static final Logger logger = LoggerFactory.getLogger(WXController.class);

	@RequestMapping(value = "/connect", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	public String validate(@RequestParam(value = "signature", required = true) String signature,
						   @RequestParam(value = "timestamp", required = true) String timestamp,
						   @RequestParam(value = "nonce", required = true) String nonce,
						   @RequestParam(value = "echostr", required = true) String echostr,
						   HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info(IOUtils.toString(request.getInputStream(),"utf-8"));

		String[] str = {WechatUtil.getWxToken(), timestamp, nonce};
		Arrays.sort(str); // 字典序排序
		String value = str[0] + str[1] + str[2];

		String sign = DigestUtils.shaHex(value);

		logger.info("token:" + WechatUtil.getWxToken() + " result:" + signature.equals(sign));
		if (signature.equals(sign)) {// 验证成功返回ehcostr
			return echostr;
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/connect", method = {RequestMethod.POST}, produces = "application/xml;charset=UTF-8")
	public String message(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xmlRequest = IOUtils.toString(request.getInputStream(),"UTF-8");
		Map<String, String> requestMap = WechatUtil.xml2Map(xmlRequest);

		// 消息类型
		String msgType = requestMap.get("MsgType");

		if(msgType.equals(MessageType.MESSAGE_TYPE_TEXT)){
			System.out.println("文本消息");
			return processNewsMessage(requestMap);
		} else if(msgType.equals(MessageType.MESSAGE_TYPE_EVENT)){
			System.out.println("事件消息");
			return processTextMessage(requestMap);
		} else {
			throw new RuntimeException("");
		}
	}

	private String processTextMessage(Map<String, String> requestMap){
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");

		// 消息内容
		String content = requestMap.get("Content");

		Long msgId = Long.parseLong(requestMap.get("MsgId"));

		TextMessage tm = new TextMessage();
		tm.setCreateTime(System.currentTimeMillis());
		tm.setFromUserName(toUserName);
		tm.setToUserName(fromUserName);
		tm.setMsgType(MessageType.MESSAGE_TYPE_TEXT);
		tm.setMsgId(msgId);
		tm.setContent("hello 你好");

		return WechatUtil.text2Xml(tm);
	}

	private String processNewsMessage(Map<String, String> requestMap){
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");

		NewsMessage newmsg=new NewsMessage();
		newmsg.setToUserName(fromUserName);
		newmsg.setFromUserName(toUserName);
		newmsg.setCreateTime(System.currentTimeMillis());
		newmsg.setMsgType(MessageType.MESSAGE_TYPE_NEWS);

		System.out.println("==============这是图片消息！");
		Article article=new Article();
		article.setDescription("这是图文消息 1"); //图文消息的描述
		article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); //图文消息图片地址
		article.setTitle("图文消息 1");  //图文消息标题
		article.setUrl("http://www.cuiyongzhi.com");  //图文 url 链接
		List<Article> list=new ArrayList<Article>();
		list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！

		Article article2=new Article();
		article2.setDescription("这abcd"); //图文消息的描述
		article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/xWUSsh6T0xn65hiagg5aX5GwVQtSvomq3tGwEticSA7pjhIicHibsfqg6wtPnRhOtqtTKEib2LN9yNZ1t1ssoaUKCdg/0"); //图文消息图片地址
		article2.setTitle("嘻嘻 1");  //图文消息标题
		article2.setUrl("http://www.baidu.com");  //图文 url 链接
		list.add(article2);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！

		newmsg.setArticleCount(list.size());
		newmsg.setArticles(list);
		return WechatUtil.news2Xml(newmsg);
	}

	private String processEventMessage(Map<String, String> requestMap) {
		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_SUBSCRIBE)) { //关注事件
			System.out.println("==============这是关注事件！");
		}

		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
			System.out.println("==============这是取消关注事件！");
		}

		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_SCAN)) { //扫描二维码事件
			System.out.println("==============这是扫描二维码事件！");
		}

		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_LOCATION)) { //位置上报事件
			System.out.println("==============这是位置上报事件！");
		}

		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
		}

		if (requestMap.get("Event").equals(MessageType.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
			System.out.println("==============这是自定义菜单 View 事件！");
		}

		return null;
	}
}
