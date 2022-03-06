package com.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AreaDAO;
import com.DAO.EmployeeDAO;


@WebServlet("/UpdateEmpStCon")
public class UpdateEmpStCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		// 상태값이 직접 넘어온 경우(수동제어)
		String status = request.getParameter("status");
		// 상태값을 자동으로 결정해야 하는 경우
		if(status.equals("")) {
			// 타입에 따라서 출근, 퇴근 등 타입을 다르게 해야 하나 일단은...
			status = "1";
		}
		EmployeeDAO dao = new EmployeeDAO();
		int cnt = dao.updateStatus(id, status);
		
		if(cnt>0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateEmpStCon");
			request.setAttribute("status", status);
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("main.jsp");
		}
	}

}
