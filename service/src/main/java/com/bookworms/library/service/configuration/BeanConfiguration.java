package com.bookworms.library.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    public JavaMailSender get() {
        return new JavaMailSenderImpl();
    }
}
