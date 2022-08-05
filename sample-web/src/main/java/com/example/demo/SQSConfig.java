package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;

/**
 * SQSの設定クラス
 */
@Configuration
public class SQSConfig {
	/**
	 * JMSのメッセージコンバータの定義
	 */
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
	
	/**
	 * JMSTemplateの定義
	 * @param sqsConnectionFactory
	 */
	@Bean
	public JmsTemplate defaultJmsTemplate(SQSConnectionFactory sqsConnectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory);
		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
		return jmsTemplate;
	}

}
