package dummy.VO;

public class LightVO {
	// 조명 순번 
    private int lightSeq;
    // 설치 위치 
    private String lightLoc;
    // 설정 조도 
    private int lightSetting;
    // 조명 상태 
    private String lightStatus;
    // 설치 일자 
    private String lightInsDate;
    // 설치자 아이디 
    private String empId;
    // 플러그 순번 
    private int plugSeq;
    // 구역 아이디 
    private String areaId;
    
	public int getLightSeq() {
		return lightSeq;
	}
	public String getLightLoc() {
		return lightLoc;
	}
	public int getLightSetting() {
		return lightSetting;
	}
	public String getLightStatus() {
		return lightStatus;
	}
	public String getLightInsDate() {
		return lightInsDate;
	}
	public String getEmpId() {
		return empId;
	}
	public int getPlugSeq() {
		return plugSeq;
	}
	public String getAreaId() {
		return areaId;
	}
    
    
}
