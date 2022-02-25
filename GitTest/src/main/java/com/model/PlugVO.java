package com.model;

public class PlugVO {
	// 플러그 순번 
    private int plugSeq;
    // 좌석 번호 
    private String seatNo;
    // 사용전력 값 
    private Double plugPower;
    // 플러그 상태 
    private String plugStatus;
    // 설치 일자 
    private String plugInsDate;
    // 구역 아이디 
    private String areaId;
    
    
    
	public int getPlugSeq() {
		return plugSeq;
	}
	public String getSeatNo() {
		return seatNo;
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

}
