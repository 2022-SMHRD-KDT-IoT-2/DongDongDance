package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;

public class RegPlugCon implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String seat = request.getParameter("seat");
		double power = Double.parseDouble(request.getParameter("power"));
		String area = request.getParameter("area");

		PlugDAO dao = new PlugDAO();
		dao.regPlug(seat, power, area);
		
		return "main2.jsp";
		
	}

}
