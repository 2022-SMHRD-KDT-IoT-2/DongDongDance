package rfid;

import java.io.IOException;
import java.io.PrintWriter;

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

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		System.out.println(uid);
		PrintWriter out = response.getWriter();
		out.println(uid);
		
//		String uid = "1234";
		RfidDAO R_dao = new RfidDAO();
		EmployeeDAO E_dao = new EmployeeDAO();
		
		String empid = R_dao.select_empid(uid);
		System.out.println(uid2);
		
		R_dao.regLog("1", empid);
		
		if(empid!=null) {
//			E_dao.updateStatus(uid, "1");
			System.out.println("해당사원이없어요.");
		}
		//1. uid가 찍히면 read하고 직원 status값이 무엇인지 가져와야함.
		//1-1. 그렇다면 찍혔을 때 확인하고 갱신해야함.
		//2. 가져온 status값에 따라 동작이 달라짐.
		
		String status =E_dao.select_status(uid);
		System.out.println("상태값 : " + status);
		
		if(status.equals("0") ) {
			E_dao.updateStatus(uid,"1");
			System.out.println("상태값이 0");
		}
		else {
			E_dao.updateStatus(uid,"0");
			System.out.println("상태값이 1");
		}
	
	}

}
