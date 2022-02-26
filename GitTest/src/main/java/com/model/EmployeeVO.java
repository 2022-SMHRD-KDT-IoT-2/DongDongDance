package com.model;

public class EmployeeVO {
	// 사원 아이디 
    private String empId;
	// 사원 비밀번호 
    private String empPw;
    // 사원 이름 
    private String empName;
    // 사원 좌석번호 
    private String empSeatNo;
    // 사원 전화 
    private String empPhone;
    // 사원 상태 
    private String empStatus;
    // 사원 가입일자 
    private String empJoindate;
    // 상사 아이디 
    private String empSuperId;
    // 관리자 여부 
    private String adminYn;
    // 알에프코드 
    private String rfidUid;
    // 구역 아이디 
    private String areaId;
    // 플러그 순번 
    private int plugSeq;
    
    // 전체
    public EmployeeVO(String empId, String empPw, String empName, String empSeatNo, String empPhone, String empStatus,
			String empJoindate, String empSuperId, String adminYn, String rfidUid, String areaId, int plugSeq) {
		super();
		this.empId = empId;
		this.empPw = empPw;
		this.empName = empName;
		this.empSeatNo = empSeatNo;
		this.empPhone = empPhone;
		this.empStatus = empStatus;
		this.empJoindate = empJoindate;
		this.empSuperId = empSuperId;
		this.adminYn = adminYn;
		this.rfidUid = rfidUid;
		this.areaId = areaId;
		this.plugSeq = plugSeq;
	}
    
    // 조회용
	public EmployeeVO(String empId, String empName, String empSeatNo, String empStatus, String empSuperId,
			String adminYn, String rfidUid, String areaId, int plugSeq) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSeatNo = empSeatNo;
		this.empStatus = empStatus;
		this.empSuperId = empSuperId;
		this.adminYn = adminYn;
		this.rfidUid = rfidUid;
		this.areaId = areaId;
		this.plugSeq = plugSeq;
	}

	// 관리자용 회원정보 수정
	public EmployeeVO(String empId, String empName, String empSeatNo, String empPhone, String empStatus,
			String empJoindate, String empSuperId, String adminYn, String rfidUid, String areaId, int plugSeq) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSeatNo = empSeatNo;
		this.empPhone = empPhone;
		this.empSuperId = empSuperId;
		this.adminYn = adminYn;
		this.rfidUid = rfidUid;
		this.areaId = areaId;
		this.plugSeq = plugSeq;
	}

	//직원용 회원정보 수정
	public EmployeeVO(String empId, String empPw, String empPhone) {
		super();
		this.empId = empId;
		this.empPw = empPw;
		this.empPhone = empPhone;

	}

	public String getEmpId() {
    	return empId;
    }
    public String getEmpPw() {
    	return empPw;
    }
    public String getEmpName() {
    	return empName;
    }
    public String getEmpSeatNo() {
    	return empSeatNo;
    }
    public String getEmpPhone() {
    	return empPhone;
    }
    public String getEmpStatus() {
    	return empStatus;
    }
    public String getEmpJoindate() {
    	return empJoindate;
    }
    public String getEmpSuperId() {
    	return empSuperId;
    }
    public String getAdminYn() {
    	return adminYn;
    }
    public String getRfidUid() {
    	return rfidUid;
    }
    public String getAreaId() {
    	return areaId;
    }
    public int getPlugSeq() {
    	return plugSeq;
    }
}
