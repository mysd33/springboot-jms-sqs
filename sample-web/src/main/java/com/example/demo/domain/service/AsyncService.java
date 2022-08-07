package com.example.demo.domain.service;

import com.example.demo.domain.model.JobRequest;

public interface AsyncService {
	void invokeAsync(JobRequest jobRequest);
}
