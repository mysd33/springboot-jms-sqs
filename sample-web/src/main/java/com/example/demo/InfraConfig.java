package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.repository.JobRequestRepository;
import com.example.demo.infra.repository.JobRequestRepositoryImpl;


/**
 * 
 * インフラストラクチャ層の設定クラス
 *
 */
@Configuration
public class InfraConfig {
	@Value("${aws.sqs.queue.name}")
	private String queueName;
	
	/**
	 * 非同期実行制御機能
	 */
	@Bean
	public JobRequestRepository jobRepository() {
		return new JobRequestRepositoryImpl(queueName);
	}
}
