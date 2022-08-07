package com.example.demo.infra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.example.demo.domain.model.JobRequest;
import com.example.demo.domain.repository.JobRequestRepository;

import lombok.RequiredArgsConstructor;
/**
 * 
 * JobRequestRepositoryの実装クラス
 * キューにSQSを使用し、JobRequestを登録する
 *
 */
@Repository
@RequiredArgsConstructor
public class JobRequestRepositoryImpl implements JobRequestRepository {
	private final JmsTemplate jmsTemplate;
	
	@Value("${aws.sqs.queue.name}")
	private String queueName;	

	
	@Override
	public void save(final JobRequest jobRequest) {
		Assert.notNull(jobRequest, "jobRequestがNullです");
		//キューに登録
		jmsTemplate.convertAndSend(queueName, jobRequest);
	}
	
}
