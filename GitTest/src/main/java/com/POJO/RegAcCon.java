package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.AirconditionerDAO;

public class RegAcCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String loc = request.getParameter("loc");
		double setting = Double.parseDouble(request.getParameter("setting"));
		String id = request.getParameter("id");
		int plug = Integer.parseInt(request.getParameter("plug"));
		String area = request.getParameter("area");

		AirconditionerDAO dao = new AirconditionerDAO();
		dao.regAc(loc, setting, id, plug, area);
		
		return "main2.jsp";
	}
	
}
