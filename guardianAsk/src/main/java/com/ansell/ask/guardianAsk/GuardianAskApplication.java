package com.ansell.ask.guardianAsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ansell.ask.common.TokenAuthenticationFilter;

@SpringBootApplication
@ComponentScan(basePackages="com.ansell.ask")
public class GuardianAskApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(GuardianAskApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("spring.devtools.restart.enabled", "false");
        return application.sources(GuardianAskApplication.class);
    }
	
	@Bean
	public FilterRegistrationBean<TokenAuthenticationFilter> sampleFilter(){
		FilterRegistrationBean<TokenAuthenticationFilter> filterSample = new FilterRegistrationBean<TokenAuthenticationFilter>();
		filterSample.setFilter(new TokenAuthenticationFilter());
		filterSample.addUrlPatterns("/**");
		return filterSample;
	}
}

