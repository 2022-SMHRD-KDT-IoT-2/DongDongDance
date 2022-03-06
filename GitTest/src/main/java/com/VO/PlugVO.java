package com.VO;

public class PlugVO {
	// 플러그 순번 
    private int plugSeq;
    // 사원 번호 
    private String id;
    // 사용전력 값 
    private Double plugPower;
    // 플러그 상태 
    private String plugStatus;
    // 설치 일자 
    private String plugInsDate;
    // 구역 아이디 
    private String areaId;
    // 연결장치 종류
    private String device;
    
	public PlugVO(int plugSeq, String id, Double plugPower, String plugStatus, String plugInsDate, String areaId, String device) {
		super();
		this.plugSeq = plugSeq;
		this.id = id;
		this.plugPower = plugPower;
		this.plugStatus = plugStatus;
		this.plugInsDate = plugInsDate;
		this.areaId = areaId;
		this.device = device;
	}
	
	
	public PlugVO(int plugSeq) {
		super();
		this.plugSeq = plugSeq;
	}



	public int getPlugSeq() {
		return plugSeq;
	}
	public String getId() {
		return id;
	}
	public Double getPlugPower() {
		return plugPower;
	}
	public String getPlugStatus() {
		return plugStatus;
	}
	public String getPlugInsDate() {
		return plugInsDate;
	}
	public String getAreaId() {
		return areaId;
	}
	public String getDevice() {
		return device;
	}

}
