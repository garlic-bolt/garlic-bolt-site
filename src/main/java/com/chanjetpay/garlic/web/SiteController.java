package com.chanjetpay.garlic.web;

import com.chanjetpay.garlic.api.BlockService;
import com.chanjetpay.garlic.common.BlockEnrollForm;
import com.chanjetpay.garlic.common.RegexValidate;
import com.chanjetpay.garlic.dto.BlockDto;
import com.chanjetpay.result.GenericResult;
import com.chanjetpay.result.Result;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by libaoa on 2017/11/8.
 */
@Controller
public class SiteController {

	private static final Logger logger  = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String from;

	@RequestMapping({"/",""})
	public String index(){
		return "index";
	}

	@RequestMapping("/instruction")
	public String instruction(){
		return "instruction";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register(@ModelAttribute BlockEnrollForm block, HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
		logger.info("enroll param - {}",block );

		ModelAndView mav = new ModelAndView("register");
		mav.addObject("block",block);

		if(request.getMethod().equals("GET")) {
			return mav;
		}

		//校验参数
		StringBuilder sb = new StringBuilder();
		if(!RegexValidate.isEmail(block.getWardenEmail())){
			sb.append("邮箱格式不正确<br>");
		}
		if(!RegexValidate.isMobile(block.getWardenPhone())){
			sb.append("手机号格式不正确<br>");
		}
		if(!RegexValidate.isChinese(block.getBlockName())){
			sb.append("社区名必须是中文<br>");
		}

		if(sb.length() != 0){
			mav.addObject("title", "信息填写错误");
			mav.addObject("message", sb.toString());
			return mav;
		}

		//信息登记
		BlockDto blockDto = new BlockDto();
		BeanUtils.copyProperties(blockDto,block);
		GenericResult<BlockDto> result = blockService.enroll(blockDto);

		if(!result.getCode().equals(Result.SUCCESS)){
			mav.addObject("title", "社区注册失败:" + result.getCode());
			mav.addObject("message", result.getDesc());
			return mav;
		}

		String blockCode = result.getData().getBlockCode();
		String inviteCode = result.getData().getInviteCode();

		String activeLink = "http://plat.coucang.com/reg/" + blockCode + "/invite/" + inviteCode;
		//发邮件
		Context context = new Context();
		context.setVariable("blockName", block.getWardenEmail());
		context.setVariable("activeLink", activeLink);
		context.setVariable("inviteCode",inviteCode);
		String mailContent = templateEngine.process("preset/reg_mail", context);

		try {
			String title = block.getBlockName() + "社区注册成功，请激活后使用" + blockCode + "01账号登录";
			sendHtmlEmail(block.getWardenEmail(), title, mailContent);
		} catch (MessagingException e) {
			logger.error("发送html邮件时发生异常！", e);

			mav.addObject("title", "社区注册失败");
			mav.addObject("message", "发送html邮件时发生异常,请于系统管理员联系");
			return mav;
		}

		//激活提示
		context.setVariable("mailHost", "http://mail.163.com");
		context.setVariable("mailAddr", block.getWardenEmail());
		String activeContent = templateEngine.process("preset/reg_active", context);

		mav.addObject("title", "注册邮件发送成功");
		mav.addObject("message", activeContent);

		return mav;
	}


	@RequestMapping("/qrlogin")
	public String qrlogin(){
		return "qrlogin";
	}

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private BlockService blockService;

	/*发送邮件的方法*/
	private void sendHtmlEmail(String to, String title, String content) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();

		//true表示需要创建一个multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(title);
		helper.setText(content, true);

		sender.send(message);
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
