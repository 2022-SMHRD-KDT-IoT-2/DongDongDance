package rfid;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.EmployeeDAO;
import com.DAO.RfidDAO;

@WebServlet("/Rfid_read")
public class Rfid_read extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid"); // 아두이노에서 uid값을 받아온다.
		PrintWriter out = response.getWriter();
		System.out.println(uid); // 출력확인
		out.println(uid); // 출력확인

//		String uid = "1234";
		RfidDAO R_dao = new RfidDAO();
		EmployeeDAO E_dao = new EmployeeDAO();

		String empid = R_dao.select_empid(uid);
		System.out.println(empid);
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
		if(time >= 0600 && time <= 1800) {
			System.out.println("정상출근");
			type = "1";
		}
		else {
			System.out.println("정상퇴근");
			type = "0";
		}
		
		if(!empid.equals("")) {			
			RequestDispatcher dispatcher = request.getRequestDispatcher("RegLogCon");
			request.setAttribute("type", type);
			request.setAttribute("id", empid);
			dispatcher.forward(request, response);
		}else {
			System.out.println("작동이 안됐습니다.");
		}


	}

}
