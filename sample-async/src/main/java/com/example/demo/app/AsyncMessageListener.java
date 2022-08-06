package com.example.demo.app;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.example.demo.domain.model.JobRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * キューを監視しジョブを実行するクラス
 *
 */
@Slf4j
@Component
public class AsyncMessageListener {
	/**
	 * キューからジョブの要求情報を受信
	 * 
	 * @param request ジョブの要求情報
	 */
	@JmsListener(destination =  "${aws.sqs.queue.name}")
	public void onMessage(@Header(JmsHeaders.MESSAGE_ID)  final String messageId, final JobRequest request) {
			String jobId = request.getJobId();
			String jobParameters = request.toParameterString();
			log.info("ジョブ実行依頼受信[MessageId:{}][JobId:{}][JobParameter:{}]", messageId,jobId, jobParameters);
	}

}