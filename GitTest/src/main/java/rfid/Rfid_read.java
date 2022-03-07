package rfid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AreaDAO;
import com.DAO.EmployeeDAO;
import com.DAO.PlugDAO;
import com.DAO.RfidDAO;
import com.VO.AreaVO;
import com.VO.EmployeeVO;
import com.VO.PlugVO;

@WebServlet("/Rfid_read")
public class Rfid_read extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String uid = request.getParameter("uid"); // 아두이노에서 uid값을 받아온다.
//		PrintWriter out = response.getWriter();
//		System.out.println(uid); // 출력확인
//		out.println(uid); // 출력확인

		
		
		
		
		String uid = "1234";
		RfidDAO R_dao = new RfidDAO();
		EmployeeDAO E_dao = new EmployeeDAO();

		String empid = R_dao.select_empid(uid);
		String status = R_dao.select_status(uid); // rfid 찍기 직전 직원 상태

		System.out.println(empid);
		System.out.println(status);
//		R_dao.regLog("1", empid); // (LOG_TYPE = 1) 로 RFID로그 - 테이블에 삽입

//		if (empid == null) {
//
//			System.out.println("해당사원이없어요.");
//		}
		// 1. uid가 찍히면 read하고 직원 status값이 무엇인지 가져와야함.
		// 1-1. 그렇다면 찍혔을 때 확인하고 갱신해야함.
		// 2. 가져온 status값에 따라 동작이 달라짐.

//		else {
//			String status = E_dao.select_status(uid);
//			System.out.println("상태값 : " + status);
//			
//			if (status.equals("0")) {
//				E_dao.updateStatus(uid, "1");
//				System.out.println("상태값이 0");
//			} else {
//				E_dao.updateStatus(uid, "0");
//				System.out.println("상태값이 1");
//			}
//		}
		
		String type = "";
		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		SimpleDateFormat fourteen_format = new SimpleDateFormat("HHmm");  // 포맷
		int time =  Integer.parseInt(fourteen_format.format(date_now));
		System.out.println(time); // 시간, 분만 나오게함.
		
		// 조건식은 바꿀 것(지각 구분)
		if(time > 0600 && time <= 1000) {
				if(status.equals("0")) {
					System.out.println("정상출근");
					type = "1";					
				}else {
					System.out.println("출근취소");
					type = "6";										
				}
		
		}else if(time > 1000 && time <= 1200) {
			if(status.equals("0")) {
				System.out.println("지각");
				type = "2";					
			}else {
				System.out.println("조퇴");
				type = "3";										
			}
		}else if(time > 1200 && time <= 1300) {
			if(status.equals("0")) {
				System.out.println("복귀");
				type = "9";					
			}else {
				System.out.println("점심");
				type = "8";										
			}
		}else if(time > 1300 && time <= 1900) {
			if(status.equals("0")) {
				System.out.println("추가");
				type = "A";					
			}else {
				System.out.println("조퇴");
				type = "3";										
			}
		}else if(time > 1900 && time <= 0600) {
			if(status.equals("0")) {
				System.out.println("추가");
				type = "A";					
			}else {
				System.out.println("퇴근");
				type = "0";										
			}
		}
		// 주말의 경우 주말출근, 주말퇴근로 설정
		
		if(type.equals("1") || type.equals("2") || type.equals("4") || type.equals("9") || type.equals("A")) {
			status = "1";
		}else {
			status = "0";
		}
		
		
		EmployeeDAO eDao = new EmployeeDAO();
		PlugDAO pDao = new PlugDAO();
		RfidDAO rDao = new RfidDAO();
		rDao.regLog(type, empid);
		
		eDao.updateStatus(empid, status);
		
		AreaDAO aDao = new AreaDAO();
		ArrayList<EmployeeVO> al1 = eDao.selectArea(); //재실자가 있는 구역
		ArrayList<EmployeeVO> al2 = eDao.selectArea2(); //퇴실구역

		if(al1.size() != 0) {
			for(int i = 0; i<al1.size(); i++) {
				aDao.updateStatus(al1.get(i).getAreaId(), "1");
			}			
		}
		if(al2.size() != 0) {			
			for(int i = 0; i<al2.size(); i++) {
				aDao.updateStatus(al2.get(i).getAreaId(), "0");				
			}
		}
		
		ArrayList<PlugVO> al3 = pDao.selectList1(empid);
		for(int i = 0; i<al3.size(); i++) {
			pDao.updateStatus(al3.get(i).getPlugSeq(), status);
		}
		
	
		ArrayList<PlugVO> al4 = pDao.selectList2("1");
		for(int i = 0; i<al4.size(); i++) {
			pDao.updateStatus(al4.get(i).getPlugSeq(), status);				
		}
		
		// 방별 제어(기본 업무시간)
		if(time > 0600 && time <= 2000) {
			ArrayList<AreaVO> al5 = aDao.getRoom("1");
			ArrayList<AreaVO> al6 = aDao.getRoom("0");
			ArrayList<ArrayList<PlugVO>> al7 = new ArrayList<ArrayList<PlugVO>>();
			ArrayList<ArrayList<PlugVO>> al8 = new ArrayList<ArrayList<PlugVO>>();
			
			
			for(int i = 0; i<al5.size(); i++) {
				al7.add(pDao.selectList3(al5.get(i).getAreaId()));
			}
			for(int i = 0; i<al6.size(); i++) {
				al8.add(pDao.selectList3(al6.get(i).getAreaId()));				
			}
			for(int i = 0; i<al8.size(); i++) {
				for(int j = 0; j<al8.get(i).size(); j++) {				
					pDao.updateStatus(al8.get(i).get(j).getPlugSeq(), "0");
					System.out.println("al8:"+al8.get(i).get(j).getPlugSeq());
				}
			}
			for(int i = 0; i<al7.size(); i++) {
				for(int j = 0; j<al7.get(i).size(); j++) {				
					pDao.updateStatus(al7.get(i).get(j).getPlugSeq(), "1");
					System.out.println("al7:"+al7.get(i).get(j).getPlugSeq());
				}
			}
			
		}else {
			// 구역별제어 (예외시간대)
			ArrayList<AreaVO> al13 = aDao.getArea("1");
			ArrayList<AreaVO> al14 = aDao.getArea("0");
			ArrayList<ArrayList<PlugVO>> al15 = new ArrayList<ArrayList<PlugVO>>();
			ArrayList<ArrayList<PlugVO>> al16 = new ArrayList<ArrayList<PlugVO>>();
			
			
			for(int i = 0; i<al13.size(); i++) {
				al15.add(pDao.selectList3(al13.get(i).getAreaId()));
			}
			for(int i = 0; i<al14.size(); i++) {
				al16.add(pDao.selectList3(al14.get(i).getAreaId()));				
			}
			for(int i = 0; i<al15.size(); i++) {
				for(int j = 0; j<al15.get(i).size(); j++) {				
					pDao.updateStatus(al15.get(i).get(j).getPlugSeq(), "1");
					System.out.println("al15:"+al15.get(i).get(j).getPlugSeq());
				}
			}
			for(int i = 0; i<al16.size(); i++) {
				for(int j = 0; j<al16.get(i).size(); j++) {				
					pDao.updateStatus(al16.get(i).get(j).getPlugSeq(), "0");
					System.out.println("al16:"+al16.get(i).get(j).getPlugSeq());
				}
			}
		}
		
		
		
		
		ArrayList<PlugVO> al9 = pDao.selectFixed("1");
		ArrayList<PlugVO> al10 = pDao.selectFixed("2");
		for(int i = 0; i<al9.size(); i++) {
			pDao.updateStatus(al9.get(i).getPlugSeq(), "1");
		}
		for(int i = 0; i<al10.size(); i++) {
			pDao.updateStatus(al10.get(i).getPlugSeq(), "0");
		}
		
		ArrayList<PlugVO> al11 = pDao.selectStatus("1");
		ArrayList<PlugVO> al12 = pDao.selectStatus("0");
		
		for(int i = 0; i<al11.size(); i++) {
			System.out.println("al11:"+al11.get(i).getPlugSeq());
		}
		for(int i = 0; i<al12.size(); i++) {
			System.out.println("al12:"+al12.get(i).getPlugSeq());
		}
		


	}

}
