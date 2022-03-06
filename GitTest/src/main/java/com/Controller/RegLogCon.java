package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.RfidDAO;


@WebServlet("/RegLogCon")
public class RegLogCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		RfidDAO dao = new RfidDAO();
		int cnt = dao.regLog(type, id);
		
		if(cnt>0) {
			
		}else {
			
		}
	}

}
