package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.PlugDAO;

public class RegPlugCon implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		double power = Double.parseDouble(request.getParameter("power"));
		String area = request.getParameter("area");
		String device = request.getParameter("device");
		System.out.println(area);
		PlugDAO dao = new PlugDAO();
		dao.regPlug(id, power, area, device);
		
		return "management.jsp";
		
	}

}
