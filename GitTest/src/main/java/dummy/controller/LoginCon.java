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


@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO vo = dao.login(id, pw);
		
		if(vo != null) {
		HttpSession session = request.getSession();
		session.setAttribute("loginvo", vo);
		if(vo.getAdminYn().equals("Y")) {			
			response.sendRedirect("main2.jsp");
		}else {
			response.sendRedirect("main1.jsp");			
		}
		}
	}

}
