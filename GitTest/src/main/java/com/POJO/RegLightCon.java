package com.POJO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Command.Command;
import com.DAO.LightDAO;

public class RegLightCon implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String loc = request.getParameter("loc");
		int setting = Integer.parseInt(request.getParameter("setting"));
		String id = request.getParameter("id");
		int plug = Integer.parseInt(request.getParameter("plug"));
		String area = request.getParameter("area");

		LightDAO dao = new LightDAO();
		dao.regLight(loc, setting, id, plug, area);
		
		return "main2.jsp";
	}

}
