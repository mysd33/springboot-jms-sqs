package com.example.demo.infra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.Assert;

import com.example.demo.domain.model.JobRequest;
import com.example.demo.domain.repository.JobRequestRepository;
/**
 * 
 * JobRequestRepositoryの実装クラス
 * キューにSQSを使用し、JobRequestを登録する
 *
 */
public class JobRequestRepositoryImpl implements JobRequestRepository {
	
	private final String queueName;	

	public JobRequestRepositoryImpl(String queueName) {
		this.queueName = queueName;
	}
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	@Override
	public void save(final JobRequest jobRequest) {
		Assert.notNull(jobRequest, "jobRequestがNullです");
		//キューに登録
		jmsTemplate.convertAndSend(queueName, jobRequest);
	}
	
}
