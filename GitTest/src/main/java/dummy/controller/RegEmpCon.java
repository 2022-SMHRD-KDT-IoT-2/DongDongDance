package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.EmployeeDAO;


@WebServlet("/RegEmpCon")
public class RegEmpCon extends HttpServlet {
     
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String seat = request.getParameter("seat");
		String phone = request.getParameter("phone");
		String superid = request.getParameter("superid");
		String yn = request.getParameter("yn");
		String rfid = request.getParameter("rfid");
		String area = request.getParameter("area");
		int plug = Integer.parseInt(request.getParameter("plug"));
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.regEmp(id, name, seat, phone, superid, yn, rfid, area, plug);
		
			if(cnt>0) {
				response.sendRedirect("main2.jsp");
			}
			else {
				System.out.println("½ÇÆÐ");
				response.sendRedirect("main2.jsp");
			}
	}

}
