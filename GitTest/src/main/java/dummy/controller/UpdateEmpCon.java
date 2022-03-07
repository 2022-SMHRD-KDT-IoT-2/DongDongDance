package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.EmployeeDAO;
import com.VO.EmployeeVO;


@WebServlet("/UpdateEmpCon")
public class UpdateEmpCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		// 직원정보 수정에서 구역번호까지 수정한 경우(-> 플러그 제어와 연결)
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String seat = request.getParameter("seat");
//		String phone = request.getParameter("phone");
//		String superid = request.getParameter("superid");
//		String yn = request.getParameter("yn");
//		String rfid = request.getParameter("rfid");
//		String area = request.getParameter("area");
//		
//		HttpSession session = request.getSession();
//		EmployeeVO vo = (EmployeeVO)session.getAttribute("loginvo");
//		String id2 = vo.getEmpId();
//		String status = vo.getEmpStatus();
//		
//		EmployeeDAO dao = new EmployeeDAO();
//		int cnt = 0;
		
//		if((id.equals(id2) || yn.equals("N")) || superid.equals("")) {
//			if((id.equals(id2)) && superid.equals("")) {
//				yn = "Y";
//			}
//			cnt = dao.updateEmp(id, pw, name, seat, phone, superid, yn, rfid);
//		}
//		
//		if((cnt > 0) && (id.equals(id2))) {
//			vo = new EmployeeVO(id, name, seat, status, superid, yn, rfid, area);
//			session.setAttribute("loginvo", vo);
//		}
//		
//		if(cnt>0) {
//			
//		}else {
//			
//		}
	}

}
