package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.JobRequest;
import com.example.demo.domain.repository.JobRequestRepository;

/**
 * 
 * ジョブ登録を依頼する汎用的な Web API
 * 汎用的なAPIにするとジョブパラメータに意味を持たせれられないので
 * その場合は、通常のWebAPIを使用する
 */
@RestController
@RequestMapping("/api/v1/")
public class JobRestController {
	@Autowired
	JobRequestRepository jobRepository;
	
	// TODO:@PostMapping化してJobRequestでもパラメータを渡せるようにする	
	@GetMapping("async/{jobId:.+}")
	public JobResponse executeBatch(			
			@PathVariable("jobId") final String jobId,
			@RequestParam("param01") final String param01,
			@RequestParam("param02") final String param02) {
		jobRepository.save(
				JobRequest.builder()
				.jobId(jobId)
				.param01(param01)
				.param02(param02)
				.build());
		return JobResponse.ACCEPT;
	}
		

}
