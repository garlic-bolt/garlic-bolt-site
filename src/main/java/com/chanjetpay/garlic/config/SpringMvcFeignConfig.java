package com.chanjetpay.garlic.config;

import com.chanjetpay.garlic.api.UserService;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by libaoa on 2017/10/12.
 */
@Configuration
//@EnableTransactionManagement //The code equals aop config or provider annotation transaction.
//@EnableAspectJAutoProxy
@PropertySource({"classpath:feign.properties"})
public class SpringMvcFeignConfig {
	private static final Logger log = LoggerFactory.getLogger(SpringMvcFeignConfig.class);

	@Value("${feign.url}")
	private String feignUrl;

	//@Bean
	//public InitializingBean init(){
	//	return new InitializingBean(){
	//		@Override
	//		public void afterPropertiesSet() throws Exception {
	//			System.out.println("这个没初始化");
	//		}
	//	};
	//}

	@Bean(name="userService")
	public UserService userService(){
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.options(new Request.Options(1000, 3500))
				.retryer(new Retryer.Default(5000, 5000, 3))
				.target(UserService.class, feignUrl);
	}
}