package com.chanjetpay.garlic.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.UUID;

/**
 * Created by libaoa on 2017/11/8.
 */
@Controller
public class GarlicSiteController {

	@Autowired
	private TemplateEngine templateEngine;

	private static final Logger logger = LoggerFactory.getLogger(GarlicSiteController.class);

	@RequestMapping({"/",""})
	public String index(){
		return "index";
	}

	@RequestMapping("/instruction")
	public String instruction(){
		return "instruction";
	}

	@RequestMapping("/register")
	public String register(){
		return "register";
	}

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private JavaMailSender sender;

	/*发送邮件的方法*/
	private void sendHtmlEmail(String to, String title, String content) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();

		//true表示需要创建一个multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(title);
		helper.setText(content, true);

		//sender.send(message);
	}

	@RequestMapping(value = "/register", method= RequestMethod.POST)
	public ModelAndView enroll(@ModelAttribute BlockEnrollDto block){
		ModelAndView mav = new ModelAndView("register");
		//信息登记
		String activeLink = "http://plat.chanjetpay.com/reg/active/xxxdddyy";
		//发邮件
		Context context = new Context();
		context.setVariable("blockName", block.getWardenEmail());
		context.setVariable("activeLink", activeLink);
		String mailContent = templateEngine.process("preset/reg_mail", context);

		try {
			sendHtmlEmail(block.getWardenEmail(), block.getBlockName() + "社区注册成功，请激活后试用", mailContent);
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！", e);
		}

		context.setVariable("mailHost", "http://mail.163.com");
		context.setVariable("mailAddr", block.getWardenEmail());
		String activeContent = templateEngine.process("preset/reg_active", context);

		mav.addObject("title", "注册邮件发送成功");
		mav.addObject("message", activeContent);

		return mav;
	}

	@RequestMapping("/question/{title}")
	public ModelAndView question(@PathVariable String title){
		ModelAndView mav = new ModelAndView();

		Context context = new Context();
		String content = templateEngine.process("content/" + title + "_content",context);
		mav.addObject("info",content);
		mav.setViewName("question");

		return mav;
	}
}
