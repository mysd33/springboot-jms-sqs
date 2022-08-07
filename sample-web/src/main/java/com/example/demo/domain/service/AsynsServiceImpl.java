package com.example.demo.domain.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.JobRequest;
import com.example.demo.domain.repository.JobRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsynsServiceImpl implements AsyncService {
	private final JobRequestRepository jobRequestRepository;

	@Override
	public void invokeAsync(JobRequest jobRequest) {
		jobRequestRepository.save(jobRequest);
	}

}
