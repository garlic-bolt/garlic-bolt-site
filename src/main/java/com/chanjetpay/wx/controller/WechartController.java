package com.chanjetpay.wx.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by libaoa on 2017/10/10.
 */
@Controller
@RequestMapping("/wechat")
public class WechartController {

	@Value("${weixin.token}")
	private String WEIXIN_TOKEN;

	@RequestMapping(value="/ttt",method = {RequestMethod.GET})
	public void test(){
		System.out.println(WEIXIN_TOKEN);
	}

	private static final Logger logger = LoggerFactory.getLogger(WechartController.class);

	@RequestMapping(value="/connect",method = {RequestMethod.GET})
	public void connectWeixin(@RequestParam(value = "signature", required = true) String signature,
							  @RequestParam(value = "timestamp", required = true) String timestamp,
							  @RequestParam(value = "nonce", required = true) String nonce,
							  @RequestParam(value = "echostr", required = true) String echostr,
							  HttpServletRequest request, HttpServletResponse response) throws IOException {


		logger.info(request.getRequestURI());

		String[] str = { WEIXIN_TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String value = str[0] + str[1] + str[2];

		String sign = DigestUtils.shaHex(value);
		PrintWriter writer = response.getWriter();

		if (signature.equals(sign)) {// 验证成功返回ehcostr
			writer.print(echostr);
		} else {
			writer.print("error");
		}

		writer.flush();
		writer.close();
	}
}
