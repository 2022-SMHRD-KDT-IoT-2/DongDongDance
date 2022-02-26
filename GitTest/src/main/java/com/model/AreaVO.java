package com.model;

public class AreaVO {
	 // 구역 아이디 
    private String areaId;
    // 재실 상태 
    private String areaStatus;
    // 구역 명 
    private String areaName;
    
	public AreaVO(String areaId, String areaStatus, String areaName) {
		super();
		this.areaId = areaId;
		this.areaStatus = areaStatus;
		this.areaName = areaName;
	}
	
	public String getAreaId() {
		return areaId;
	}
	public String getAreaStatus() {
		return areaStatus;
	}
	public String getAreaName() {
		return areaName;
	}
}
