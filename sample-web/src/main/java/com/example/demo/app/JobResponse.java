package com.example.demo.app;

import java.io.Serializable;

import lombok.Getter;

/**
 * 
 * ジョブ処理依頼の受理結果を返却するクラス
 *
 */
public class JobResponse implements Serializable {

	@Getter
	private final String result;

	// 受理
	public static final JobResponse ACCEPT = new JobResponse("accept");
	// 拒絶
	public static final JobResponse Reject = new JobResponse("reject");

	private static final long serialVersionUID = 8603261817673346760L;

	private JobResponse(final String result) {
		this.result = result;
	}

}
