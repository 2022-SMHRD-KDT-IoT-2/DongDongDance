package dummy.VO;

public class AirconditionerVO {
	// 냉난방 순번 
    private int acSeq;
    // 설치 위치 
    private String acLoc;
    // 설정 온도 
    private Double acSetting;
    // 냉난방 상태 
    private String acStatus;
    // 설치 일자 
    private String acInsDate;
    // 설치자 아이디 
    private String empId;
    // 플러그 순번 
    private int plugSeq;
    // 구역 아이디 
    private String areaId;
    
	public int getAcSeq() {
		return acSeq;
	}
	public String getAcLoc() {
		return acLoc;
	}
	public Double getAcSetting() {
		return acSetting;
	}
	public String getAcStatus() {
		return acStatus;
	}
	public String getAcInsDate() {
		return acInsDate;
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
