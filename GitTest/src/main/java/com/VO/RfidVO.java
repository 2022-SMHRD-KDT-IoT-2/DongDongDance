package com.VO;

public class RfidVO {
	// 로그 순번 
    private int logSeq;
    // 로그 구분 
    private String logType;
    // 로그 시간 
    private String logTime;
    // 사원 아이디 
    private String empId;
    // 로그 메모 
    private String logMemo;
    
    
	public RfidVO(int logSeq, String logType, String logTime, String logMemo) {
		super();
		this.logSeq = logSeq;
		this.logType = logType;
		this.logTime = logTime;
		this.logMemo = logMemo;
	}
	
	public int getLogSeq() {
		return logSeq;
	}
	public String getLogType() {
		return logType;
	}
	public String getLogTime() {
		return logTime;
	}
	public String getEmpId() {
		return empId;
	}
	public String getLogMemo() {
		return logMemo;
	}
    
}
