package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AreaDAO;

@WebServlet("/A_joincon")
public class A_joincon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("area_i_id");
		String name = request.getParameter("area_i_name");

		System.out.println(id + name);
		AreaDAO dao = new AreaDAO();
		int cnt = dao.regArea(id, name);

		if (cnt > 0) {
			System.out.println("등록성공");
			response.sendRedirect("A_crud.jsp");
//			response.sendRedirect("#");
		} else {
			System.out.println("등록실패");
//			response.sendRedirect("#");
		}
	}

}
