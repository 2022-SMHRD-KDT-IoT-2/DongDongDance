package dummy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.EmployeeDAO;


@WebServlet("/DeleteEmpCon")
public class DeleteEmpCon extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
		
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.deleteEmp(id);
		
		if(cnt > 0) {
			response.sendRedirect("main2.jsp");
		}else {
			response.sendRedirect("main2.jsp");
		}
	}

}
